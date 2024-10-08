package org.karel.karel.node;

import org.karel.karel.compiler.Functions;
import org.karel.karel.karelmap.KarelMap;
import org.karel.karel.Node;
import org.karel.karel.NodeVisitor;

public class RepeatNode implements Node {
    private final int count;
    private final Node node;
    private final Node next;

    public RepeatNode(int count, Node body, Node next) {
        this.count = count;
        this.node = body;
        this.next = next;
    }

    public KarelMap accept(NodeVisitor visitor, KarelMap map, Functions functions){
        return visitor.accept(this, map, functions);
    }

    public int getCount() {
        return count;
    }

    public Node getNode() {
        return node;
    }

    public Node getNext() {
        return next;
    }
}
