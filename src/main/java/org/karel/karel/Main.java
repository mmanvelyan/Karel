package org.karel.karel;

import org.karel.karel.compiler.*;
import org.karel.karel.karelmap.KarelMap;
import org.karel.karel.node.EmptyNode;
import org.karel.karel.node.FunctionCallNode;
import org.karel.karel.nodevisitor.RunNodeVisitor;
import org.karel.karel.problem.ProblemJdbcRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        try {

            System.out.println(context.getBean(ProblemJdbcRepository.class));

            String program = readProgram("src/main/resources/program.txt");
            KarelMap km = createMap("src/main/resources/map.txt");
            System.out.println(km);
            Functions functions = new Functions();
            Node start = compile(program, functions);

            KarelMap resultMap = execute(km, functions, start);

            System.out.println(resultMap.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static KarelMap execute(KarelMap km, Functions functions, Node start) {
        km = start.accept(new RunNodeVisitor(), km, functions);
        return km;
    }

    private static Node compile(String program, Functions functions) {
        Parser parser = new Parser();
        Lexer lex = new Lexer(program);
        parser.parse(lex, functions);
        return new FunctionCallNode("main", new EmptyNode());
    }

    private static KarelMap createMap(String fileName) throws IOException {
        String mapData = Files.readString(Paths.get(fileName), StandardCharsets.UTF_8);
        return new KarelMap(mapData);
    }

    private static String readProgram(String fileName) throws IOException {
        return Files.readString(Paths.get(fileName), StandardCharsets.UTF_8);
    }
}