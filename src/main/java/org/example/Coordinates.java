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
        if (!(o instanceof Coordinates)){
            return false;
        }
        Coordinates c = (Coordinates) o;
        if (x == c.getX() && y == c.getY() && direction == c.getDirection()){
            return true;
        } else {
            return false;
        }
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
