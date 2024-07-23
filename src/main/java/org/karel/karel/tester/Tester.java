package org.karel.karel.tester;
import org.karel.karel.compiler.*;
import org.karel.karel.compiler.condition.InvalidOperationException;
import java.util.List;
import java.util.concurrent.*;

class RunTest implements Callable<KarelMap> {
    private final KarelMap inputMap;
    private final Node start;
    private final Functions functions;

    public RunTest(Node start, KarelMap inputMap, Functions functions) {
        this.start = start;
        this.inputMap = inputMap;
        this.functions = functions;
    }

    @Override
    public KarelMap call() {
        return start.accept(new RunNodeVisitor(), inputMap, functions);
    }
}

public class Tester {

    private Node compile(String program, Functions functions) {
        Parser parser = new Parser();
        Lexer lex = new Lexer(program);
        parser.parse(lex, functions);
        return new FunctionCallNode("main", new EmptyNode());
    }

    public Status testAll(String program, List<Test> tests){
        Functions functions = new Functions();
        Node start;
        try {
            start = compile(program, functions);
        } catch (UnexpectedTokenException e) {
            return Status.COMPILATION_ERROR;
        }
        for (Test test : tests){
            KarelMap inputMap = new KarelMap(test.input());
            KarelMap expectedMap = new KarelMap(test.output());
            KarelMap resultMap = null;
            RunTest runTest = new RunTest(start, inputMap, functions);
            try {
                ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(2);
                Future<KarelMap> future = executorService.submit(runTest);
                Runnable cancelTask = () -> {
                    future.cancel(true);
                };
                executorService.schedule(cancelTask, 5000, TimeUnit.MILLISECONDS);
                executorService.shutdown();
                resultMap = future.get();
            } catch (CancellationException e) {
                return Status.TIME_LIMIT_EXCEEDED;
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause instanceof InvalidOperationException) {
                    return Status.RUNTIME_ERROR;
                } else if (cause instanceof UnexpectedFunctionException){
                    return Status.COMPILATION_ERROR;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (resultMap == null || !resultMap.equals(expectedMap)) {
                return Status.WRONG_ANSWER;
            }
        }
        return Status.ACCEPTED;
    }
}
