package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.example.Direction.*;

public class RunTest {

    private void run(String program, KarelMap map){
        Functions functions = new Functions();
        Parser parser = new Parser();
        Lexer lex = new Lexer(program);
        parser.parse(lex, functions);
        Node main = functions.getFunction("main");
        main.accept(new RunNodeVisitor(), map, functions);
    }

    @Test
    public void testMove(){
        String program = """
                function main(){
                    move();
                }
                """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        run(program, map);
        Assertions.assertEquals(2, map.getX());
        Assertions.assertEquals(1, map.getY());
        Assertions.assertEquals(0, map.getBag());
        Assertions.assertEquals(EAST, map.getDirection());
    }

    @Test
    public void testTurnLeft(){
        String program = """
                function main(){
                    turnLeft();
                }
                """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        run(program, map);
        Assertions.assertEquals(1, map.getX());
        Assertions.assertEquals(1, map.getY());
        Assertions.assertEquals(0, map.getBag());
        Assertions.assertEquals(NORTH, map.getDirection());
    }

    @Test
    public void testPutBeeper(){
        String program = """
                function main(){
                    putBeeper();
                }
                """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 1, EAST, new HashMap<>(), new HashSet<>());
        run(program, map);
        Assertions.assertEquals(1, map.getX());
        Assertions.assertEquals(1, map.getY());
        Assertions.assertEquals(0, map.getBag());
        Assertions.assertEquals(1, map.getBeepers().get(new Coordinates(1, 1)));
        Assertions.assertEquals(EAST, map.getDirection());
    }

    @Test
    public void testPickBeeper(){
        String program = """
                function main(){
                    pickBeeper();
                }
                """;
        Map<Coordinates, Integer> beepers = new HashMap<>();
        beepers.put(new Coordinates(1, 1), 1);
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, beepers, new HashSet<>());
        run(program, map);
        Assertions.assertEquals(1, map.getX());
        Assertions.assertEquals(1, map.getY());
        Assertions.assertEquals(1, map.getBag());
        Assertions.assertEquals(0, map.getBeepers().get(new Coordinates(1, 1)));
        Assertions.assertEquals(EAST, map.getDirection());
    }

    @Test
    public void testIfTrue(){
        String program = """
                function main(){
                    if (beepersInBag()){
                        putBeeper();
                    }
                }
                """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 1, EAST, new HashMap<>(), new HashSet<>());
        run(program, map);
        Assertions.assertEquals(1, map.getX());
        Assertions.assertEquals(1, map.getY());
        Assertions.assertEquals(0, map.getBag());
        Assertions.assertEquals(1, map.getBeepers().get(new Coordinates(1, 1)));
        Assertions.assertEquals(EAST, map.getDirection());
    }

    @Test
    public void testIfFalse(){
        String program = """
                function main(){
                    if (beepersInBag()){
                        putBeeper();
                    }
                }
                """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        run(program, map);
        Assertions.assertEquals(1, map.getX());
        Assertions.assertEquals(1, map.getY());
        Assertions.assertEquals(0, map.getBag());
        Assertions.assertNull(map.getBeepers().get(new Coordinates(1, 1)));
        Assertions.assertEquals(EAST, map.getDirection());
    }

    @Test
    public void testIfElse(){
        String program = """
                function main(){
                    if (beepersInBag()){
                        putBeeper();
                    } else {
                        move();
                    }
                }
                """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        run(program, map);
        Assertions.assertEquals(2, map.getX());
        Assertions.assertEquals(1, map.getY());
        Assertions.assertEquals(0, map.getBag());
        Assertions.assertNull(map.getBeepers().get(new Coordinates(1, 1)));
        Assertions.assertEquals(EAST, map.getDirection());
    }

    @Test
    public void testWhile(){
        String program = """
                function main(){
                    while (frontIsClear()){
                        move();
                    }
                }
                """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        run(program, map);
        Assertions.assertEquals(5, map.getX());
        Assertions.assertEquals(1, map.getY());
        Assertions.assertEquals(0, map.getBag());
        Assertions.assertEquals(EAST, map.getDirection());
    }

    @Test
    public void testRepeat(){
        String program = """
                function main(){
                    repeat (3){
                        move();
                    }
                }
                """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        run(program, map);
        Assertions.assertEquals(4, map.getX());
        Assertions.assertEquals(1, map.getY());
        Assertions.assertEquals(0, map.getBag());
        Assertions.assertEquals(EAST, map.getDirection());
    }

    @Test
    public void testFunction(){
        String program = """
                function turnRight(){
                    turnLeft();
                    turnLeft();
                    turnLeft();
                }
                
                function main(){
                    turnRight();
                }
                """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        run(program, map);
        Assertions.assertEquals(1, map.getX());
        Assertions.assertEquals(1, map.getY());
        Assertions.assertEquals(0, map.getBag());
        Assertions.assertEquals(SOUTH, map.getDirection());
    }


}
