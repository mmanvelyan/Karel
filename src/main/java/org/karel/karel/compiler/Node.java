package org.karel.karel.compiler;

public interface Node {
    KarelMap accept(NodeVisitor visitor, KarelMap map, Functions functions);
}
