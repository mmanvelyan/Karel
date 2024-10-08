package org.karel.karel.node;

import org.karel.karel.compiler.Functions;
import org.karel.karel.karelmap.KarelMap;
import org.karel.karel.Node;
import org.karel.karel.NodeVisitor;
import org.karel.karel.Condition;

public class IfNode implements Node {
    private final Condition cond;
    private final Node nodeIf;
    private final Node nodeElse;
    private final Node next;

    public IfNode(Condition cond, Node body, Node bodyElse, Node next) {
        this.cond = cond;
        this.nodeIf = body;
        this.nodeElse = bodyElse;
        this.next = next;
    }

    public KarelMap accept(NodeVisitor visitor, KarelMap map, Functions functions){
        return visitor.accept(this, map, functions);
    }

    public Condition getCond() {
        return cond;
    }

    public Node getNodeIf() {
        return nodeIf;
    }

    public Node getNodeElse() {
        return nodeElse;
    }

    public Node getNext() {
        return next;
    }
}
