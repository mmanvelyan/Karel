package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String program = """
                function turnRight(){
                \tturnLeft();
                \tturnLeft();
                \tturnLeft();
                }\t

                function main(){
                \twhile (frontIsClear()){
                \t\tmove();
                \t}
                \twhile (frontIsBlocked()){
                \t\tturnLeft();
                \t\tmove();
                \t\tturnRight();
                \t\tmove();
                \t\tif (beepersPresent()){
                \t\t\tpickBeeper();
                \t\t}
                \t}
                }
                """;
        Parser parser = new Parser();
        Functions functions = new Functions();
        Lexer lex = new Lexer(program);
        parser.parse(lex, functions);
        Map<Coordinates, Integer> beepers = new HashMap<>();
        beepers.put(new Coordinates(3, 2), 1);
        beepers.put(new Coordinates(5, 4), 1);
        Set<Coordinates> walls = new HashSet<>();
        walls.add(new Coordinates(2, 1, Direction.EAST));
        walls.add(new Coordinates(3, 2, Direction.SOUTH));
        walls.add(new Coordinates(3, 2, Direction.EAST));
        walls.add(new Coordinates(4, 3, Direction.SOUTH));
        walls.add(new Coordinates(4, 3, Direction.EAST));
        walls.add(new Coordinates(5, 4, Direction.SOUTH));
        walls.add(new Coordinates(5, 4, Direction.EAST));
        walls.add(new Coordinates(6, 5, Direction.SOUTH));
        walls.add(new Coordinates(6, 5, Direction.EAST));
        walls.add(new Coordinates(7, 6, Direction.SOUTH));
        walls.add(new Coordinates(7, 5, Direction.EAST));
        walls.add(new Coordinates(7, 4, Direction.EAST));
        walls.add(new Coordinates(7, 3, Direction.EAST));
        walls.add(new Coordinates(7, 2, Direction.EAST));
        walls.add(new Coordinates(7, 1, Direction.EAST));
        System.out.println(walls.contains(new Coordinates(7, 3, Direction.EAST)));
        KarelMap km = new KarelMap(9, 9, 1, 1, 0, Direction.EAST, beepers, walls);
        System.out.println(program);
        Node start = new FunctionCallNode("main", new EmptyNode());
        km = start.accept(new RunNodeVisitor(), km, functions);
        km.print();
    }
}