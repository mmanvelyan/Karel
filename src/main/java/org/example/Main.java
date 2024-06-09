package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        try {
            String url = "jdbc:postgresql://larin-vb:5432/postgres?user=postgres&password=qwerty&ssl=false";
            Connection conn = DriverManager.getConnection(url);
            Collection<String> users = selectUsers(conn);
            System.out.println(users);

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

    private static Collection<String> selectUsers(Connection conn) {
        String s = "' OR ''='";
        String query = "SELECT ID, USERNAME, EMAIL, PASSWORD FROM USERS WHERE USERNAME = "+"\'"+s+"\'";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)){
            ArrayList<String> users = new ArrayList<>();
            while (rs.next()) {
                String username = rs.getString("USERNAME");
                users.add(username);
            }
            return users;
        } catch (SQLException e) {
            System.err.println(e.toString());
            throw new RuntimeException(e);
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