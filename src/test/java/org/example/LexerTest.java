package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.example.TokenType.*;

public class LexerTest {
    @Test
    public void lexerName(){
        Lexer lex = new Lexer("name");
        Token next = lex.nextToken();
        Assertions.assertEquals(NAME, next.getType());
        Assertions.assertEquals("name", next.getName());
    }

    @Test
    public void lexerNumber(){
        Lexer lex = new Lexer("123");
        Token next = lex.nextToken();
        Assertions.assertEquals(NUMBER, next.getType());
        Assertions.assertEquals(123, next.getValue());
    }

    @Test
    public void lexerFunction(){
        Lexer lex = new Lexer("function");
        Token next = lex.nextToken();
        Assertions.assertEquals(FUNCTION, next.getType());
    }

    @Test
    public void lexerEnd(){
        Lexer lex = new Lexer("");
        Token next = lex.nextToken();
        Assertions.assertEquals(END, next.getType());
    }

    @Test
    public void lexerSpaces(){
        Lexer lex = new Lexer("   (");
        Token next = lex.nextToken();
        Assertions.assertEquals(ROUND_BR_OPEN, next.getType());
    }

    @Test
    public void lexerNT(){
        Lexer lex = new Lexer("\n\n  \t }");
        Token next = lex.nextToken();
        Assertions.assertEquals(CURLY_BR_CLOSE, next.getType());
    }

    @Test
    public void lexerUnexpected(){
        Lexer lex = new Lexer("#");
        Assertions.assertThrows(UnexpectedTokenException.class, lex::nextToken);
    }

    @Test
    public void lexerPrevToken(){
        Lexer lex = new Lexer(";");
        Token next = lex.nextToken();
        Assertions.assertEquals(SEMICOLON, next.getType());
        lex.prevToken();
        next = lex.nextToken();
        Assertions.assertEquals(SEMICOLON, next.getType());
    }
}
