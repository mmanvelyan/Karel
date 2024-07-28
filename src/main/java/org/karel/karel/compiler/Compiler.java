package org.karel.karel.compiler;

public class Compiler {

    public CompiledProgram compile(String program) {
        Lexer lexer = new Lexer(program);
        Parser parser = new Parser();
        Functions functions = new Functions();
        try {
            parser.parse(lexer, functions);
            return new CompiledProgram(functions.getFunction("main"), functions);
        } catch (UnexpectedTokenException e){
            throw new CompilationException(e);
        }
    }

}
