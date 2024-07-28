package org.karel.karel.compiler;

public class CompilationException extends RuntimeException {

    public CompilationException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage(){
        return "Compilation Error : " + getCause().getMessage();
    }
}
