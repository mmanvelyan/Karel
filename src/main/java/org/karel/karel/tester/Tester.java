package org.karel.karel.tester;
import org.karel.karel.compiler.*;
import org.karel.karel.compiler.Compiler;
import org.karel.karel.compiler.condition.InvalidOperationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

class RunTest implements Callable<KarelMap> {
    private final KarelMap inputMap;
    private final CompiledProgram compiledProgram;

    public RunTest(KarelMap inputMap, CompiledProgram compiledProgram) {
        this.inputMap = inputMap;
        this.compiledProgram = compiledProgram;
    }

    @Override
    public KarelMap call() {
        return compiledProgram.run(inputMap);
    }
}

@Component
public class Tester {

    private final Compiler compiler;

    public Tester(Compiler compiler) {
        this.compiler = compiler;
    }

    public Status testAll(String program, List<Test> tests){
        CompiledProgram compiledProgram;
        try {
            compiledProgram = compiler.compile(program);
        } catch (UnexpectedTokenException e) {
            return Status.COMPILATION_ERROR;
        }
        for (Test test : tests) {
            Status status = runTest(test, compiledProgram);
            if (status != Status.ACCEPTED) return status;
        }
        return Status.ACCEPTED;
    }

    private static Status runTest(Test test, CompiledProgram compiledProgram) {
        KarelMap inputMap = new KarelMap(test.input());
        KarelMap expectedMap = new KarelMap(test.output());
        KarelMap resultMap = null;
        RunTest runTest = new RunTest(inputMap, compiledProgram);
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
        return Status.ACCEPTED;
    }
}
