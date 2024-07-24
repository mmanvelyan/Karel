package org.karel.karel;

import org.karel.karel.compiler.Functions;
import org.karel.karel.karelmap.KarelMap;

public interface Node {
    KarelMap accept(NodeVisitor visitor, KarelMap map, Functions functions);
}
