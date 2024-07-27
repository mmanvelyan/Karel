package org.karel.karel.compiler;

public class Compiler {

    public CompiledProgram compile(String program) {
        Lexer lexer = new Lexer(program);
        Parser parser = new Parser();
        Functions functions = new Functions();
        parser.parse(lexer, functions);
        return new CompiledProgram(functions.getFunction("main"), functions);
    }

}
