package org.example;

public class RunNodeVisitor implements NodeVisitor {
    @Override
    public void accept(EmptyNode node, KarelMap map, Functions functions) {

    }

    @Override
    public void accept(FunctionCallNode node, KarelMap map, Functions functions) {
        String name = node.getFuncName();
        Node function = functions.getFunction(name);
        function.accept(this, map, functions);
        node.getNext().accept(this, map, functions);
    }

    @Override
    public void accept(IfNode node, KarelMap map, Functions functions) {
        Condition condition = node.getCond();
        if (map.checkCondition(condition)){
            node.getNodeIf().accept(this, map, functions);
        } else {
            node.getNodeElse().accept(this, map, functions);
        }
        node.getNext().accept(this, map, functions);
    }

    @Override
    public void accept(RepeatNode node, KarelMap map, Functions functions) {
        for (int i = 0; i < node.getCount(); i++){
            node.getNode().accept(this, map, functions);
        }
        node.getNext().accept(this, map, functions);
    }

    @Override
    public void accept(OperationNode node, KarelMap map, Functions functions) {
        switch (node.getOperation()){
            case MOVE -> map.move();
            case TURN_LEFT -> map.turnLeft();
            case PUT_BEEPER -> map.putBeeper();
            case PICK_BEEPER -> map.pickBeeper();
        }
        node.getNext().accept(this, map, functions);
    }

    @Override
    public void accept(WhileNode node, KarelMap map, Functions functions) {
        Condition condition = node.getCond();
        while (map.checkCondition(condition)){
            node.getNode().accept(this, map, functions);
        }
        node.getNext().accept(this, map, functions);
    }
}
