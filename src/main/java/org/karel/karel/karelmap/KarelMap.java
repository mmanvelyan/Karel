package org.karel.karel.karelmap;

import java.util.*;

import static org.karel.karel.karelmap.Direction.*;

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

    public KarelMap(String file) {
        Scanner input = new Scanner(file);
        if (!input.hasNextInt()){
            throw new InvalidMapException();
        }
        int rows = input.nextInt();
        if (rows < 1 || !input.hasNextInt()){
            throw new InvalidMapException();
        }
        int cols = input.nextInt();
        if (cols < 1 || !input.hasNextInt()){
            throw new InvalidMapException();
        }
        int x = input.nextInt();
        if (x < 1 || x > cols || !input.hasNextInt()){
            throw new InvalidMapException();
        }
        int y = input.nextInt();
        if (y < 1 || y > rows || !input.hasNext()){
            throw new InvalidMapException();
        }
        String dirs = input.next();
        Direction dir;
        if (Objects.equals(dirs, "N")){
            dir = NORTH;
        } else if (Objects.equals(dirs, "E")){
            dir = EAST;
        } else if (Objects.equals(dirs, "S")){
            dir = SOUTH;
        } else if (Objects.equals(dirs, "W")){
            dir = WEST;
        } else {
            throw new InvalidMapException();
        }
        if (!input.hasNextInt()){
            throw new InvalidMapException();
        }
        int bag = input.nextInt();
        if (bag < 0 || bag >= 100){
            throw new InvalidMapException();
        }
        Set<Coordinates> walls = new HashSet<>();
        Map<Coordinates, Integer> beepers = new HashMap<>();
        while (input.hasNext()){
            String type = input.next();
            if (!input.hasNextInt()){
                throw new InvalidMapException();
            }
            int a = input.nextInt();
            if (a < 1 || a > cols || !input.hasNextInt()){
                throw new InvalidMapException();
            }
            int b = input.nextInt();
            if (b < 1 || b > rows){
                throw new InvalidMapException();
            }
            if (Objects.equals(type, "B")){
                if (!input.hasNextInt()){
                    throw new InvalidMapException();
                }
                int beep = input.nextInt();
                if (beep < 0 || beep >= 100){
                    throw new InvalidMapException();
                }
                beepers.put(new Coordinates(a, b), beep);
            } else if (Objects.equals(type, "W")){
                if (!input.hasNext()){
                    throw new InvalidMapException();
                }
                String d = input.next();
                if (Objects.equals(d, "N")){
                    walls.add(new Coordinates(a, b, NORTH));
                } else if (Objects.equals(d, "E")){
                    walls.add(new Coordinates(a, b, EAST));
                } else if (Objects.equals(d, "S")){
                    walls.add(new Coordinates(a, b, SOUTH));
                } else if (Objects.equals(d, "W")){
                    walls.add(new Coordinates(a, b, WEST));
                } else {
                    throw new InvalidMapException();
                }
            } else {
                throw new InvalidMapException();
            }
        }
        wallsMap = new WallsMap(rows, cols, walls);
        beepersMap = new BeepersMap(beepers);
        robotPosition = new RobotPosition(x, y, dir, bag);
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

    @Override
    public String toString() {
        List<String> result = new ArrayList<>();
        result.add(" CORNER  FACING  BEEP-BAG  BEEP-CORNER\n");
        result.add(" (" + getX() + ", " + getY() + ")");
        if (getDirection() == Direction.EAST || getDirection() == Direction.WEST) {
            result.add(" ");
        }
        result.add("   " + getDirection() + "      " + getBag() + "         " + getBeepersCount()+"\n");
        int rows = wallsMap.getRows();
        int cols = wallsMap.getCols();
        for (int i = 2 * rows; i >= 0; i--) {
            if (i % 2 == 1) {
                result.add(" " + (i / 2 + 1) + " ");
            } else {
                result.add("   ");
            }
            for (int j = 0; j < 2 * cols + 1; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    if ((i == 0 && j == 0) || (i == 2 * rows && j == 0) || (i == 0 && j == 2 * cols) || (i == 2 * rows && j == 2 * cols)) {
                        result.add("+");
                    } else {
                        if (i == 0 || i == 2 * rows) {
                            result.add("-");
                        } else if (j == 0 || j == 2 * cols) {
                            result.add("|");
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
                                result.add("+");
                            } else if (hor == 2) {
                                result.add("-");
                            } else if (ver == 2) {
                                result.add("|");
                            } else {
                                result.add(" ");
                            }
                        }
                    }
                } else if (i % 2 == 0) {
                    if (i == 0 || i == 2 * rows || hasWall(new Coordinates(j / 2 + 1, i / 2, NORTH))) {
                        result.add("---");
                    } else {
                        result.add("   ");
                    }
                } else if (j % 2 == 0) {
                    if (j == 0 || j == 2 * cols || hasWall(new Coordinates(j / 2, i / 2 + 1, Direction.EAST))) {
                        result.add("|");
                    } else {
                        result.add(" ");
                    }
                } else if (j / 2 == getX() - 1 && i / 2 == getY() - 1) {
                    switch (getDirection()) {
                        case NORTH -> result.add(" ^ ");
                        case EAST -> result.add(" > ");
                        case SOUTH -> result.add(" v ");
                        case WEST -> result.add(" < ");
                    }
                } else {
                    int x = beepersMap.getBeepersCount(new Coordinates(j / 2+1, i / 2+1));
                    if (x > 0) {
                        if (x < 10) {
                            result.add(" ");
                        }
                        result.add(String.valueOf(x));
                        if (x < 100) {
                            result.add(" ");
                        }
                    } else {
                        result.add(" . ");
                    }
                }
            }
            result.add("\n");
        }
        result.add("   ");
        for (int i = 1; i <= cols; i++) {
            result.add("  " + i + " ");
        }
        result.add("\n");
        return String.join("", result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KarelMap karelMap)) return false;
        return Objects.equals(wallsMap, karelMap.wallsMap) && Objects.equals(beepersMap, karelMap.beepersMap) && Objects.equals(robotPosition, karelMap.robotPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wallsMap, beepersMap, robotPosition);
    }
}







