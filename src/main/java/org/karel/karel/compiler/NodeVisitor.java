package org.karel.karel.compiler;

public interface NodeVisitor {
    KarelMap accept(EmptyNode node, KarelMap map, Functions functions);
    KarelMap accept(FunctionCallNode node, KarelMap map, Functions functions);
    KarelMap accept(IfNode node, KarelMap map, Functions functions);
    KarelMap accept(RepeatNode node, KarelMap map, Functions functions);
    KarelMap accept(OperationNode node, KarelMap map, Functions functions);
    KarelMap accept(WhileNode node, KarelMap map, Functions functions);
}
