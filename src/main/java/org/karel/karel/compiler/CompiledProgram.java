package org.karel.karel.compiler;

public class CompiledProgram {

    private final Node start;
    private final Functions functions;

    public CompiledProgram(Node start, Functions functions) {
        this.start = start;
        this.functions = functions;
    }

    public KarelMap run(KarelMap inputMap){
        return start.accept(new RunNodeVisitor(), inputMap, functions);
    }

}
