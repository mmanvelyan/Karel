package org.karel.karel.compiler;

import java.util.Objects;

public class RobotPosition {
    private final Coordinates position;
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

    public Coordinates getPosition() {
        return position;
    }

    public Direction getDirection(){
        return position.getDirection();
    }

    public int getBag() {
        return bag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RobotPosition that)) return false;
        return bag == that.bag && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, bag);
    }
}
