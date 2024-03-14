package org.example;

public class RobotPosition {
    Coordinates position;
    private final int bag;

    public RobotPosition(Coordinates position, int bag){
        this.position = position;
        this.bag = bag;
    }

    public RobotPosition(int x, int y, Direction direction, int bag){
        this(new Coordinates(x, y, direction), bag);
    }

    public int getX(){
        return position.getX();
    }

    public int getY(){
        return position.getY();
    }

    public Direction getDirection(){
        return position.getDirection();
    }

    public int getBag() {
        return bag;
    }
}
