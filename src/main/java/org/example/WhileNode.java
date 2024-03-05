package org.example;

public class WhileNode implements Node {
    private final Condition cond;
    private final Node node;
    private final Node next;

    public WhileNode(Condition cond, Node body, Node next) {
        this.cond = cond;
        this.node = body;
        this.next = next;
    }

    public void accept(NodeVisitor visitor, KarelMap map, Functions functions){
        visitor.accept(this, map, functions);
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
