package org.karel.karel.node;

import org.karel.karel.compiler.Functions;
import org.karel.karel.karelmap.KarelMap;
import org.karel.karel.Node;
import org.karel.karel.NodeVisitor;
import org.karel.karel.Condition;

public class WhileNode implements Node {
    private final Condition cond;
    private final Node node;
    private final Node next;

    public WhileNode(Condition cond, Node body, Node next) {
        this.cond = cond;
        this.node = body;
        this.next = next;
    }

    public KarelMap accept(NodeVisitor visitor, KarelMap map, Functions functions){
        return visitor.accept(this, map, functions);
    }

    public Condition getCond() {
        return cond;
    }

    public Node getNode() {
        return node;
    }

    public Node getNext() {
        return next;
    }
}
