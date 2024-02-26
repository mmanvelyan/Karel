package org.example;

public class RepeatNode implements Node {
    private final int count;
    private final Node node;
    private final Node next;

    public RepeatNode(int count, Node body, Node next) {
        this.count = count;
        this.node = body;
        this.next = next;
    }
}
