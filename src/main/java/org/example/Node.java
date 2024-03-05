package org.example;

public interface Node {
    void accept(NodeVisitor visitor, KarelMap map, Functions functions);
}
