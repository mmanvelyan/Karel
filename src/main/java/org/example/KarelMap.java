package org.example;

import java.util.Map;
import java.util.Set;

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

    public int getRows(){
        return wallsMap.getRows();
    }

    public int getCols(){
        return wallsMap.getCols();
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
}







