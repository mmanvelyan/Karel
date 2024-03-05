package org.example;

public class OperationNode implements Node{
    private final Operation operation;

    private final Node next;

    public OperationNode(Operation operation, Node next) {
        this.operation = operation;
        this.next = next;
    }

    public void accept(NodeVisitor visitor, KarelMap map, Functions functions){
        visitor.accept(this, map, functions);
    }

    public Operation getOperation() {
        return operation;
    }

    public Node getNext() {
        return next;
    }
}
