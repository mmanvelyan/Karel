package org.example;

public interface NodeVisitor {
    void accept(EmptyNode node, KarelMap map, Functions functions);
    void accept(FunctionCallNode node, KarelMap map, Functions functions);
    void accept(IfNode node, KarelMap map, Functions functions);
    void accept(RepeatNode node, KarelMap map, Functions functions);
    void accept(OperationNode node, KarelMap map, Functions functions);
    void accept(WhileNode node, KarelMap map, Functions functions);
}
