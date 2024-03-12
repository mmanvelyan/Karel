package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.example.Direction.*;
import static org.example.TokenType.*;

public class UnexpectedTokenExceptionTest {

    private void run(String program, KarelMap map) {
        Functions functions = new Functions();
        Parser parser = new Parser();
        Lexer lex = new Lexer(program);
        parser.parse(lex, functions);
        Node main = functions.getFunction("main");
        main.accept(new RunNodeVisitor(), map, functions);
    }

    @Test
    public void missingSemicolon(){
        String program =
            """
            function main(){
                move()
            }
            """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        UnexpectedTokenException e = Assertions.assertThrows(UnexpectedTokenException.class, () -> run(program, map));
        Assertions.assertEquals(3, e.getStr());
        Assertions.assertEquals(1, e.getPos());
        Assertions.assertEquals(CURLY_BR_CLOSE, e.getToken().getType());
    }

    @Test
    public void missingCloseBr(){
        String program =
            """
            function main(){
                move(;
            }
            """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        UnexpectedTokenException e = Assertions.assertThrows(UnexpectedTokenException.class, () -> run(program, map));
        Assertions.assertEquals(2, e.getStr());
        Assertions.assertEquals(10, e.getPos());
        Assertions.assertEquals(SEMICOLON, e.getToken().getType());
    }

    @Test
    public void missingOpenBr(){
        String program =
            """
            function main()
                move();
            }
            """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        UnexpectedTokenException e = Assertions.assertThrows(UnexpectedTokenException.class, () -> run(program, map));
        Assertions.assertEquals(2, e.getStr());
        Assertions.assertEquals(8, e.getPos());
        Assertions.assertEquals(NAME, e.getToken().getType());
    }

    @Test
    public void wrongConditionName(){
        String program =
            """
            function main(){
                if (frontispiece()){
                    move();
                }
            }
            """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        UnexpectedTokenException e = Assertions.assertThrows(UnexpectedTokenException.class, () -> run(program, map));
        Assertions.assertEquals(2, e.getStr());
        Assertions.assertEquals(20, e.getPos());
        Assertions.assertEquals(NAME, e.getToken().getType());
    }

    @Test
    public void wrongEnd(){
        String program =
            """
            function main(){
                if (frontIsClear()){
                    move();
                }
            }
            }
            """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        UnexpectedTokenException e = Assertions.assertThrows(UnexpectedTokenException.class, () -> run(program, map));
        Assertions.assertEquals(6, e.getStr());
        Assertions.assertEquals(1, e.getPos());
        Assertions.assertEquals(CURLY_BR_CLOSE, e.getToken().getType());
    }

    @Test
    public void wrongFunctionName(){
        String program =
            """
            function 123(){
                move();
            }
            
            function main(){
                if (frontIsClear()){
                    move();
                }
            }
            """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        UnexpectedTokenException e = Assertions.assertThrows(UnexpectedTokenException.class, () -> run(program, map));
        Assertions.assertEquals(1, e.getStr());
        Assertions.assertEquals(12, e.getPos());
        Assertions.assertEquals(NUMBER, e.getToken().getType());
    }

    @Test
    public void wrongNodeName(){
        String program =
            """
            function main(){
                iff (frontIsClear()){
                    move();
                }
            }
            """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        UnexpectedTokenException e = Assertions.assertThrows(UnexpectedTokenException.class, () -> run(program, map));
        Assertions.assertEquals(2, e.getStr());
        Assertions.assertEquals(21, e.getPos());
        Assertions.assertEquals(NAME, e.getToken().getType());
    }

    @Test
    public void invalidChar(){
        String program =
            """
            function ma#in(){
                iff (frontIsClear()){
                    move();
                }
            }
            """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        UnexpectedTokenException e = Assertions.assertThrows(UnexpectedTokenException.class, () -> run(program, map));
        Assertions.assertEquals(1, e.getStr());
        Assertions.assertEquals(12, e.getPos());
        Assertions.assertEquals(UNEXPECTED, e.getToken().getType());
    }

    @Test
    public void noFunction(){
        String program =
            """
            move();
            
            function ma#in(){
                iff (frontIsClear()){
                    move();
                }
            }
            """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        UnexpectedTokenException e = Assertions.assertThrows(UnexpectedTokenException.class, () -> run(program, map));
        Assertions.assertEquals(1, e.getStr());
        Assertions.assertEquals(4, e.getPos());
        Assertions.assertEquals(NAME, e.getToken().getType());
    }

    @Test
    public void repeatNoNumber(){
        String program =
            """
            function main(){
                repeat (frontIsClear()){
                    move();
                }
            }
            """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        UnexpectedTokenException e = Assertions.assertThrows(UnexpectedTokenException.class, () -> run(program, map));
        Assertions.assertEquals(2, e.getStr());
        Assertions.assertEquals(24, e.getPos());
        Assertions.assertEquals(NAME, e.getToken().getType());
    }

    @Test
    public void whileNoCondition(){
        String program =
            """
            function main(){
                while (3){
                    move();
                }
            }
            """;
        KarelMap map = new KarelMap(5, 5, 1, 1, 0, EAST, new HashMap<>(), new HashSet<>());
        UnexpectedTokenException e = Assertions.assertThrows(UnexpectedTokenException.class, () -> run(program, map));
        Assertions.assertEquals(2, e.getStr());
        Assertions.assertEquals(12, e.getPos());
        Assertions.assertEquals(NUMBER, e.getToken().getType());
    }



}
