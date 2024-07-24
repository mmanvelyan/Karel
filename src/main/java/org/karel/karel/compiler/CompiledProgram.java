package org.karel.karel.compiler;

import org.karel.karel.Node;
import org.karel.karel.karelmap.KarelMap;
import org.karel.karel.nodevisitor.RunNodeVisitor;

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
