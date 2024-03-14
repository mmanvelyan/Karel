package org.example;

import java.util.HashSet;
import java.util.Set;

public class WallsMap {
    private final int rows;
    private final int cols;
    private final Set<Coordinates> walls;

    public WallsMap(int rows, int cols, Set<Coordinates> walls){
        this.rows = rows;
        this.cols = cols;
        this.walls = walls;
    }

    public WallsMap(int rows, int cols){
        this(rows, cols, new HashSet<>());
    }

    boolean hasWall(int x, int y, Direction direction){
        return switch (direction){
            case NORTH -> (y == rows) || walls.contains(new Coordinates(x, y, Direction.NORTH)) || walls.contains(new Coordinates(x, y+1, Direction.SOUTH));
            case EAST -> (x == cols) || walls.contains(new Coordinates(x, y, Direction.EAST)) || walls.contains(new Coordinates(x+1, y, Direction.WEST));
            case SOUTH -> (y == 1) || walls.contains(new Coordinates(x, y, Direction.SOUTH)) || walls.contains(new Coordinates(x, y-1, Direction.NORTH));
            case WEST -> (x == 1) || walls.contains(new Coordinates(x, y, Direction.WEST)) || walls.contains(new Coordinates(x-1, y, Direction.SOUTH));
        };
    }
}
