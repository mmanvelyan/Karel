package org.example;

public interface Node {
    KarelMap accept(NodeVisitor visitor, KarelMap map, Functions functions);
}
