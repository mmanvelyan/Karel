package org.karel.karel.node;

import org.karel.karel.compiler.Functions;
import org.karel.karel.karelmap.KarelMap;
import org.karel.karel.Node;
import org.karel.karel.NodeVisitor;

public class FunctionCallNode implements Node {
    private final String funcName;
    private final Node next;

    public FunctionCallNode(String name, Node next) {
        this.funcName = name;
        this.next = next;
    }

    public KarelMap accept(NodeVisitor visitor, KarelMap map, Functions functions){
        return visitor.accept(this, map, functions);
    }

    public String getFuncName() {
        return funcName;
    }

    public Node getNext() {
        return next;
    }
}
