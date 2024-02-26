package org.example;

public class FunctionCallNode implements Node {
    private final String funcName;
    private final Node next;

    public FunctionCallNode(String name, Node next) {
        this.funcName = name;
        this.next = next;
    }
}
