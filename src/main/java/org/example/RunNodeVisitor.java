package org.example;

import java.util.Map;

public class RunNodeVisitor implements NodeVisitor {
    @Override
    public KarelMap accept(EmptyNode node, KarelMap map, Functions functions) {
        return map;
    }

    @Override
    public KarelMap accept(FunctionCallNode node, KarelMap map, Functions functions) {
        String name = node.getFuncName();
        Node function = functions.getFunction(name);
        map = function.accept(this, map, functions);
        return node.getNext().accept(this, map, functions);
    }

    @Override
    public KarelMap accept(IfNode node, KarelMap map, Functions functions) {
        Condition condition = node.getCond();
        if (map.checkCondition(condition)){
            map = node.getNodeIf().accept(this, map, functions);
        } else {
            map = node.getNodeElse().accept(this, map, functions);
        }
        return node.getNext().accept(this, map, functions);
    }

    @Override
    public KarelMap accept(RepeatNode node, KarelMap map, Functions functions) {
        for (int i = 0; i < node.getCount(); i++){
            map = node.getNode().accept(this, map, functions);
        }
        return node.getNext().accept(this, map, functions);
    }

    @Override
    public KarelMap accept(OperationNode node, KarelMap map, Functions functions) {
        switch (node.getOperation()){
            case MOVE -> {
                RobotPosition rp = map.getRobotPosition();
                if (map.hasWall(rp.getPosition())){
                    throw new RuntimeException();
                } else {
                    map = new KarelMap(map.getWallsMap(), map.getBeepersMap(), new RobotPosition(rp.getPosition().getNext(), rp.getBag()));
                }
            }
            case TURN_LEFT -> {
                RobotPosition rp = map.getRobotPosition();
                map = new KarelMap(map.getWallsMap(), map.getBeepersMap(), new RobotPosition(rp.getPosition().getLeft(), rp.getBag()));
            }
            case PUT_BEEPER -> {
                RobotPosition rp = map.getRobotPosition();
                if (rp.getBag() == 0){
                    throw new RuntimeException();
                }
                BeepersMap beepersMap = map.getBeepersMap();
                Map<Coordinates, Integer> beepers = beepersMap.getBeepers();
                beepers.put(rp.getPosition(), beepersMap.getBeepersCount(rp.getPosition())+1);
                map = new KarelMap(map.getWallsMap(), new BeepersMap(beepers), new RobotPosition(rp.getPosition(), rp.getBag()-1));
            }
            case PICK_BEEPER -> {
                RobotPosition rp = map.getRobotPosition();
                BeepersMap beepersMap = map.getBeepersMap();
                int b = beepersMap.getBeepersCount(rp.getPosition());
                if (b == 0){
                    throw new RuntimeException();
                }
                Map<Coordinates, Integer> beepers = beepersMap.getBeepers();
                beepers.put(rp.getPosition(), b-1);
                map = new KarelMap(map.getWallsMap(), new BeepersMap(beepers), new RobotPosition(rp.getPosition(), rp.getBag()+1));
            }
        }
        return node.getNext().accept(this, map, functions);
    }

    @Override
    public KarelMap accept(WhileNode node, KarelMap map, Functions functions) {
        Condition condition = node.getCond();
        while (map.checkCondition(condition)){
            map = node.getNode().accept(this, map, functions);
        }
        return node.getNext().accept(this, map, functions);
    }
}
