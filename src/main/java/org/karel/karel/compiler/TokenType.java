package org.karel.karel.compiler;

public enum TokenType {
    NUMBER("number"),
    NAME("name"),
    ROUND_BR_OPEN("("),
    ROUND_BR_CLOSE(")"),
    CURLY_BR_OPEN("{"),
    CURLY_BR_CLOSE("}"),
    FUNCTION("function"),
    REPEAT("repeat"),
    WHILE("while"),
    IF("if"),
    ELSE("else"),
    SEMICOLON(";"),
    END("end of program"),
    UNEXPECTED("invalid char");

    private final String description;

    TokenType (String description){
        this.description = description;
    }

    @Override
    public String toString(){
        return description;
    }
}
