package org.example;

import java.util.Map;
import java.util.Set;

public class KarelMap {
    private final int rows;
    private final int cols;
    private int x;
    private int y;
    private int bag;
    private Direction direction;
    private final Map<Coordinates, Integer> beepers;
    private final Set<Coordinates> walls;

    public KarelMap(int rows, int cols, int x, int y, int bag, Direction direction, Map<Coordinates, Integer> beepers, Set<Coordinates> walls) {
        this.rows = rows;
        this.cols = cols;
        this.x = x;
        this.y = y;
        this.bag = bag;
        this.direction = direction;
        this.beepers = beepers;
        this.walls = walls;
    }

    public void move(){
        switch (direction) {
            case NORTH:
                if (y == rows || walls.contains(new Coordinates(x, y, Direction.NORTH)) || walls.contains(new Coordinates(x, y + 1, Direction.SOUTH))) {
                    throw new RuntimeException();
                }
                y++;
                break;
            case EAST:
                if (x == cols || walls.contains(new Coordinates(x, y, Direction.EAST)) || walls.contains(new Coordinates(x + 1, y, Direction.WEST))) {
                    throw new RuntimeException();
                }
                x++;
                break;
            case SOUTH:
                if (y == 1 || walls.contains(new Coordinates(x, y - 1, Direction.NORTH)) || walls.contains(new Coordinates(x, y, Direction.SOUTH))) {
                    throw new RuntimeException();
                }
                y--;
                break;
            case WEST:
                if (x == 1 || walls.contains(new Coordinates(x - 1, y, Direction.EAST)) || walls.contains(new Coordinates(x, y, Direction.WEST))) {
                    throw new RuntimeException();
                }
                x--;
                break;
        }
    }

    public void turnLeft(){
        switch (direction){
            case NORTH:
                direction = Direction.WEST;
                break;
            case EAST:
                direction = Direction.NORTH;
                break;
            case SOUTH:
                direction = Direction.EAST;
                break;
            case WEST:
                direction = Direction.SOUTH;
                break;
        }
    }

    public void putBeeper(){
        if (bag == 0){
            throw new RuntimeException();
        }
        bag--;
        if (beepers.get(new Coordinates(x, y)) == null){
            beepers.put(new Coordinates(x, y), 1);
        } else {
            beepers.put(new Coordinates(x, y), beepers.get(new Coordinates(x, y))+1);
        }
    }

    public void pickBeeper(){
        if (beepers.get(new Coordinates(x, y)) == null || beepers.get(new Coordinates(x, y)) == 0){
            throw new RuntimeException();
        } else {
            beepers.put(new Coordinates(x, y), beepers.get(new Coordinates(x, y))-1);
            bag++;
        }
    }

    public void print(){
        System.out.println(" CORNER  FACING  BEEP-BAG  BEEP-CORNER");
        System.out.print(" ("+x+", "+y+")");
        if (direction == Direction.EAST || direction == Direction.WEST){
            System.out.print(" ");
        }
        System.out.print("   "+direction+"      "+bag+"         ");
        if (beepers.get(new Coordinates(x, y)) != null){
            System.out.println(beepers.get(new Coordinates(x, y)));
        } else {
            System.out.println(0);
        }
        for (int i = 2*rows; i >= 0; i--){
            if (i%2 == 1){
                System.out.print(" "+(i/2+1)+" ");
            } else {
                System.out.print("   ");
            }
            for (int j = 0; j < 2*cols+1; j++) {
                if (i%2 == 0 && j%2 == 0){
                    if ((i == 0 && j == 0) || (i == 2*rows && j == 0) || (i == 0 && j == 2*cols) || (i == 2*rows && j == 2*cols)) {
                        System.out.print("+");
                    } else {
                        if (i == 0 || i == 2*rows){
                            System.out.print("-");
                        } else if (j == 0 || j == 2*cols){
                            System.out.print("|");
                        } else {
                            int ver = 0, hor = 0;
                            if ((i > 0 && j > 0 && walls.contains(new Coordinates(j / 2, i / 2, Direction.EAST))) ||
                                    (i > 0 && j < 2 * cols && walls.contains(new Coordinates(j / 2+1, i / 2, Direction.WEST)))) {
                                ver++;
                            }
                            if ((i > 0 && j > 0 && walls.contains(new Coordinates(j / 2, i / 2, Direction.NORTH))) ||
                                    (i < 2 * rows && j > 0 && walls.contains(new Coordinates(j / 2, i / 2+1, Direction.SOUTH)))) {
                                hor++;
                            }
                            if ((i > 0 && j < 2 * cols && walls.contains(new Coordinates(j / 2+1, i / 2, Direction.NORTH))) ||
                                    (i < 2 * rows && j < 2 * cols && walls.contains(new Coordinates(j / 2+1, i / 2+1, Direction.SOUTH)))) {
                                hor++;
                            }
                            if ((i < 2 * rows && j > 0 && walls.contains(new Coordinates(j / 2, i / 2+1, Direction.EAST))) ||
                                    (i < 2 * rows && j < 2 * cols && walls.contains(new Coordinates(j / 2+1, i / 2+1, Direction.WEST)))) {
                                ver++;
                            }
                            if ((hor > 0 && ver > 0) || hor == 1 || ver == 1) {
                                System.out.print("+");
                            } else if (hor == 2) {
                                System.out.print("-");
                            } else if (ver == 2) {
                                System.out.print("|");
                            } else {
                                System.out.print(" ");
                            }
                        }
                    }
                } else if (i%2 == 0 && j%2 != 0) {
                    if ((i == 0 || i == 2 * rows ||
                                    walls.contains(new Coordinates(j / 2+1, i / 2, Direction.NORTH)) ||
                                    walls.contains(new Coordinates(j / 2+1, i / 2+1, Direction.SOUTH)))
                    ) {
                        System.out.print("---");
                    } else {
                        System.out.print("   ");
                    }
                } else if (i%2 != 0 && j%2 == 0){
                    if ((j == 0 || j == 2*cols ||
                                    walls.contains(new Coordinates(j/2, i/2+1, Direction.EAST)) ||
                                    walls.contains(new Coordinates(j/2+1, i/2+1, Direction.WEST)))
                    ){
                        System.out.print("|");
                    } else {
                        System.out.print(" ");
                    }
                } else if (i%2 != 0 && j%2 != 0 && j/2 == x-1 && i/2 == y-1){
                    switch (direction){
                        case NORTH:
                            System.out.print(" ^ ");
                            break;
                        case EAST:
                            System.out.print(" > ");
                            break;
                        case SOUTH:
                            System.out.print(" v ");
                            break;
                        case WEST:
                            System.out.print(" < ");
                            break;
                    }
                } else if (i%2 != 0 && j%2 != 0){
                    int x = 0;
                    if (beepers.get(new Coordinates(j/2+1, i/2+1)) != null) {
                        x = beepers.get(new Coordinates(j / 2+1, i / 2+1));
                    }
                    if (x > 0){
                        System.out.print(" ");
                        System.out.print(x);
                        System.out.print(" ");
                    } else {
                        System.out.print(" . ");
                    }
                }
            }
            System.out.println();
        }
        System.out.print("   ");
        for (int i = 1; i <= cols; i++){
            System.out.print("  "+i+" ");
        }
        System.out.println();
    }

}







