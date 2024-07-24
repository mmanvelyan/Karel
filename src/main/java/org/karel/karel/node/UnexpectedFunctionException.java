package org.karel.karel.node;

public class UnexpectedFunctionException extends RuntimeException {
    private final String name;

    public UnexpectedFunctionException(String name) {
        this.name = name;
    }

    @Override
    public String getMessage(){
        return "Unexpected function name : " + "'" + name + "'";
    }
}
