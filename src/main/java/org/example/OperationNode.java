package org.example;

public class OperationNode implements Node{
    private final Operation operation;

    private final Node next;

    public OperationNode(Operation operation, Node next) {
        this.operation = operation;
        this.next = next;
    }
}
