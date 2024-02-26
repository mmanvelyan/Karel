package org.example;
public class Token {
    private final TokenType type;
    private final String name;
    private final int value;

    public Token(TokenType type, String name, int value){
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public Token(TokenType type){
        this(type, "", 0);
    }

    public Token(int value){
        this(TokenType.NUMBER, "", value);
    }

    public Token(String name){
        this(TokenType.NAME, name, 0);
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
