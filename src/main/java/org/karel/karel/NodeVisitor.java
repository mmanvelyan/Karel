package org.karel.karel;

import org.karel.karel.compiler.Functions;
import org.karel.karel.karelmap.KarelMap;
import org.karel.karel.node.*;

public interface NodeVisitor {
    KarelMap accept(EmptyNode node, KarelMap map, Functions functions);
    KarelMap accept(FunctionCallNode node, KarelMap map, Functions functions);
    KarelMap accept(IfNode node, KarelMap map, Functions functions);
    KarelMap accept(RepeatNode node, KarelMap map, Functions functions);
    KarelMap accept(OperationNode node, KarelMap map, Functions functions);
    KarelMap accept(WhileNode node, KarelMap map, Functions functions);
}
