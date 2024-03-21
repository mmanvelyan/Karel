package org.example;

import java.util.Map;
import java.util.Set;

import static org.example.Direction.*;

public class KarelMap {

    private final WallsMap wallsMap;
    private final BeepersMap beepersMap;
    private final RobotPosition robotPosition;

    public KarelMap(WallsMap wallsMap, BeepersMap beepersMap, RobotPosition robotPosition) {
        this.wallsMap = wallsMap;
        this.beepersMap = beepersMap;
        this.robotPosition = robotPosition;
    }

    public KarelMap(int rows, int cols, int x, int y, int bag, Direction direction, Map<Coordinates, Integer> beepers, Set<Coordinates> walls) {
        this(new WallsMap(rows, cols, walls), new BeepersMap(beepers), new RobotPosition(x, y, direction, bag));
    }

    public KarelMap(int rows, int cols, int x, int y, int bag, Direction direction){
        this(new WallsMap(rows, cols), new BeepersMap(), new RobotPosition(x, y, direction, bag));
    }

    public KarelMap(int rows, int cols, Coordinates pos, int bag, Map<Coordinates, Integer> beepers, Set<Coordinates> walls){
        this(new WallsMap(rows, cols, walls), new BeepersMap(beepers), new RobotPosition(pos, bag));
    }

    public boolean hasWall(int x, int y, Direction direction){
        return wallsMap.hasWall(x, y, direction);
    }

    public boolean hasWall(Coordinates coordinates){
        return hasWall(coordinates.getX(), coordinates.getY(), coordinates.getDirection());
    }

    public int getBeepersCount(){
        return beepersMap.getBeepersCount(robotPosition.getPosition());
    }

    public WallsMap getWallsMap() {
        return wallsMap;
    }

    public BeepersMap getBeepersMap() {
        return beepersMap;
    }

    public RobotPosition getRobotPosition() {
        return robotPosition;
    }

    public int getX(){
        return robotPosition.getX();
    }

    public int getY(){
        return robotPosition.getY();
    }

    public Direction getDirection(){
        return robotPosition.getDirection();
    }

    public Coordinates getRobotCoordinates(){
        return robotPosition.getPosition();
    }

    public int getBag(){
        return robotPosition.getBag();
    }

    public void print() {
        System.out.println(" CORNER  FACING  BEEP-BAG  BEEP-CORNER");
        System.out.print(" (" + getX() + ", " + getY() + ")");
        if (getDirection() == Direction.EAST || getDirection() == Direction.WEST) {
            System.out.print(" ");
        }
        System.out.println("   " + getDirection() + "      " + getBag() + "         " + getBeepersCount());
        int rows = wallsMap.getRows();
        int cols = wallsMap.getCols();
        for (int i = 2 * rows; i >= 0; i--) {
            if (i % 2 == 1) {
                System.out.print(" " + (i / 2 + 1) + " ");
            } else {
                System.out.print("   ");
            }
            for (int j = 0; j < 2 * cols + 1; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    if ((i == 0 && j == 0) || (i == 2 * rows && j == 0) || (i == 0 && j == 2 * cols) || (i == 2 * rows && j == 2 * cols)) {
                        System.out.print("+");
                    } else {
                        if (i == 0 || i == 2 * rows) {
                            System.out.print("-");
                        } else if (j == 0 || j == 2 * cols) {
                            System.out.print("|");
                        } else {
                            int ver = 0, hor = 0;
                            if (hasWall(new Coordinates(j / 2, i / 2, Direction.EAST))) {
                                ver++;
                            }
                            if (hasWall(new Coordinates(j / 2, i / 2, NORTH))) {
                                hor++;
                            }
                            if (hasWall(new Coordinates(j / 2 + 1, i / 2, NORTH))) {
                                hor++;
                            }
                            if (hasWall(new Coordinates(j / 2, i / 2 + 1, Direction.EAST))) {
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
                } else if (i % 2 == 0) {
                    if (i == 0 || i == 2 * rows || hasWall(new Coordinates(j / 2 + 1, i / 2, NORTH))) {
                        System.out.print("---");
                    } else {
                        System.out.print("   ");
                    }
                } else if (j % 2 == 0) {
                    if (j == 0 || j == 2 * cols || hasWall(new Coordinates(j / 2, i / 2 + 1, Direction.EAST))) {
                        System.out.print("|");
                    } else {
                        System.out.print(" ");
                    }
                } else if (j / 2 == getX() - 1 && i / 2 == getY() - 1) {
                    switch (getDirection()) {
                        case NORTH -> System.out.print(" ^ ");
                        case EAST -> System.out.print(" > ");
                        case SOUTH -> System.out.print(" v ");
                        case WEST -> System.out.print(" < ");
                    }
                } else {
                    int x = beepersMap.getBeepersCount(new Coordinates(j / 2, i / 2));
                    if (x > 0) {
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
        for (int i = 1; i <= cols; i++) {
            System.out.print("  " + i + " ");
        }
        System.out.println();
    }
}







