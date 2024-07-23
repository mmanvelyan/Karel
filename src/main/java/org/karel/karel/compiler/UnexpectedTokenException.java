package org.karel.karel.compiler;

import java.util.ArrayList;
import java.util.Arrays;

public class UnexpectedTokenException extends RuntimeException {
    private final Token got;
    private final ArrayList<String> exp = new ArrayList<>();

    public int getStr(){
        return got.getStr();
    }

    public int getPos(){
        return got.getPos();
    }

    public Token getToken(){
        return got;
    }

    public String getMessage(){
        return (got.getStr()+":"+got.getPos() + " expected '" + String.join("' or '", exp) + "', got '" + got.getType().toString() + "'");
    }

    public UnexpectedTokenException(Token got, String... exp) {
        this.got = got;
        this.exp.addAll(Arrays.asList(exp));
    }
}

