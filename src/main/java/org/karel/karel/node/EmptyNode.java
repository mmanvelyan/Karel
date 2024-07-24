package org.karel.karel.node;

import org.karel.karel.compiler.Functions;
import org.karel.karel.karelmap.KarelMap;
import org.karel.karel.Node;
import org.karel.karel.NodeVisitor;

public class EmptyNode implements Node {
    public KarelMap accept(NodeVisitor visitor, KarelMap map, Functions functions){
        return visitor.accept(this, map, functions);
    }
}
