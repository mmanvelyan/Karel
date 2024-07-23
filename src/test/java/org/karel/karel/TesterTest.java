package org.karel.karel;

import org.karel.karel.tester.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.karel.karel.tester.Status.*;

public class TesterTest {

    private final Test test = new Test(1,
                                    1,
                                        "3 3 1 1 N 0 W 1 1 N B 1 1 1",
                                       "3 3 1 1 N 1 W 1 1 N");
    private final Tester tester = new Tester();

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
