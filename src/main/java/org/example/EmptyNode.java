package org.example;

public class EmptyNode implements Node {
    public void accept(NodeVisitor visitor, KarelMap map, Functions functions){
        visitor.accept(this, map, functions);
    }
}
