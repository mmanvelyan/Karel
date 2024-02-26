package org.example;

public class UnexpectedTokenException extends RuntimeException {
    private final int pos;
    private final Token got;

    public UnexpectedTokenException(int pos, Token token) {
        this.pos = pos;
        this.got = token;
    }

    public String getMessage(){
        return "pos "+pos+" : Unexpected token " + got.getType();
    }
}
