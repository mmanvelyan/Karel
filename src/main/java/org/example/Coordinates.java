package org.example;

import java.util.Objects;

public class Coordinates {
    private final int x;
    private final int y;
    private final Direction direction;

    public Coordinates(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Coordinates(int x, int y) {
        this(x, y, Direction.NORTH);
    }

    public Coordinates getLeft(){
        return switch (direction){
            case EAST -> new Coordinates(x, y, Direction.NORTH);
            case SOUTH -> new Coordinates(x, y, Direction.EAST);
            case WEST -> new Coordinates(x, y, Direction.SOUTH);
            case NORTH -> new Coordinates(x, y, Direction.WEST);
        };
    }

    public Coordinates getNext(){
        return switch (direction){
            case EAST -> new Coordinates(x+1, y, Direction.EAST);
            case SOUTH -> new Coordinates(x, y-1, Direction.SOUTH);
            case WEST -> new Coordinates(x-1, y, Direction.WEST);
            case NORTH -> new Coordinates(x, y+1, Direction.NORTH);
        };
    }

    public Coordinates getRight(){
        return this.getLeft().getLeft().getLeft();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (!(o instanceof Coordinates c)){
            return false;
        }
        return x == c.getX() && y == c.getY() && direction == c.getDirection();
    }

    @Override
    public String toString(){
        return "Coordinates{x="+x+", y="+y+", direction="+direction+"}";
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y, direction);
    }
}
