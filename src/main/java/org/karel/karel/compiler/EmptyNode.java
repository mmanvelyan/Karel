package org.karel.karel.compiler;

public class EmptyNode implements Node {
    public KarelMap accept(NodeVisitor visitor, KarelMap map, Functions functions){
        return visitor.accept(this, map, functions);
    }
}
