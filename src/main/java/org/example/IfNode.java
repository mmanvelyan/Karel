package org.example;

public class IfNode implements Node{
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
}
