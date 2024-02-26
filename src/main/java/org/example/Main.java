package org.example;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String program = "function turnRight(){\n" +
                "\tturnLeft();\n" +
                "\tturnLeft();\n" +
                "\tturnLeft();\n" +
                "}\n" +
                "\n" +
                "function main(){\n" +
                "\tmove();\n" +
                "\tputBeeper();\n" +
                "\tif (leftIsClear()){\n" +
                "\t\tmove();\n" +
                "\t}\n" +
                "\trepeat (3) {\n" +
                "\t\tputBeeper();\n" +
                "\t\twhile (beepersPresent()){\n" +
                "\t\t\tpickBeeper();\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\tif (rightIsBlocked()){\n" +
                "\t\tmove();\n" +
                "\t} else {\n" +
                "\t\tturnLeft();\n" +
                "\t}\n" +
                "\tturnLeft();\n" +
                "\tturnRight();\n" +
                "}\n";
        Parser parser = new Parser();
        Functions functions = new Functions();
        Lexer lex = new Lexer(program, 0);
        parser.parse(lex, functions);
        System.out.println(program);
    }
}