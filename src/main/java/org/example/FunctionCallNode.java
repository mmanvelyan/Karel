package org.example;

public class FunctionCallNode implements Node {
    private final String funcName;
    private final Node next;

    public FunctionCallNode(String name, Node next) {
        this.funcName = name;
        this.next = next;
    }

    public void accept(NodeVisitor visitor, KarelMap map, Functions functions){
        visitor.accept(this, map, functions);
    }

    public String getFuncName() {
        return funcName;
    }

    public Node getNext() {
        return next;
    }
}
