package org.example;

public class RobotPosition {
    Coordinates position;

    public RobotPosition(Coordinates position){
        this.position = position;
    }

    public RobotPosition(int x, int y, Direction direction){
        this(new Coordinates(x, y, direction));
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
}
