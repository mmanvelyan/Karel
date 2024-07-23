package org.karel.karel.compiler;

public class Token {
    private final int pos;
    private final int str;
    private final TokenType type;
    private final String name;
    private final int value;

    public Token(int str, int pos, TokenType type, String name, int value){
        this.str = str;
        this.pos = pos;
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public Token(int str, int pos, TokenType type){
        this(str, pos, type, "", 0);
    }

    public Token(int str, int pos, int value){
        this(str, pos, TokenType.NUMBER, "", value);
    }

    public Token(int str, int pos, String name){
        this(str, pos, TokenType.NAME, name, 0);
    }

    public Token(int str, int pos, TokenType type, String token) {
        this(str, pos, type, token, 0);
    }


    public int getPos() {
        return pos;
    }

    public int getStr() {
        return str;
    }

    public TokenType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
