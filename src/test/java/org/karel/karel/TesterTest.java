package org.karel.karel;

import org.karel.karel.tester.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.karel.karel.tester.Status.*;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TesterTest {

    private final Test test = new Test(1,
                                    1,
                                        "3 3 1 1 N 0 W 1 1 N B 1 1 1",
                                       "3 3 1 1 N 1 W 1 1 N");
    @Autowired
    private Tester tester;

    @org.junit.jupiter.api.Test
    public void testOK(){
        String program = """
                            function main(){
                                pickBeeper();
                            }
                         """;
        Status res = tester.testAll(program, List.of(test));
        assertEquals(ACCEPTED, res);
    }

    @org.junit.jupiter.api.Test
    public void testWrongAnswer(){
        String program = """
                            function main(){
                                turnLeft();
                            }
                         """;
        Status res = tester.testAll(program, List.of(test));
        assertEquals(WRONG_ANSWER, res);
    }

    @org.junit.jupiter.api.Test
    public void testUnexpectedToken(){
        String program = """
                            function main(){
                                turnLeft()
                            }
                         """;
        Status res = tester.testAll(program, List.of(test));
        assertEquals(COMPILATION_ERROR, res);
    }

    @org.junit.jupiter.api.Test
    public void testUnexpectedFunction(){
        String program = """
                            function main(){
                                turnLet();
                            }
                         """;
        Status res = tester.testAll(program, List.of(test));
        assertEquals(COMPILATION_ERROR, res);
    }

    @org.junit.jupiter.api.Test
    public void testInvalidOperation(){
        String program = """
                            function main(){
                                move();
                            }
                         """;
        Status res = tester.testAll(program, List.of(test));
        assertEquals(RUNTIME_ERROR, res);
    }

    @org.junit.jupiter.api.Test
    public void testTimeLimit(){
        String program = """
                            function main(){
                                while (facingNorth()){
                                    turnLeft();
                                    turnLeft();
                                    turnLeft();
                                    turnLeft();
                                }
                            }
                         """;
        Status res = tester.testAll(program, List.of(test));
        assertEquals(TIME_LIMIT_EXCEEDED, res);
    }

}
