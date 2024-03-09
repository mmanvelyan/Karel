package org.example;

public class Lexer {
    private final String str;
    private int pos;
    private int line;
    private int linePos;
    private int prevLine;
    private int prevLinePos;
    private int prevPos;

    public Lexer(String str){
        this.str = str;
        this.line = 1;
        this.pos = 0;
        this.linePos = 1;
        this.prevPos = 0;
        this.prevLine = 0;
        this.prevLinePos = 0;
    }

    public Token nextToken(){
        prevPos = pos;
        prevLine = line;
        prevLinePos = linePos;
        while (pos < str.length() && (str.charAt(pos) == ' ' || str.charAt(pos) == '\n' || str.charAt(pos) == '\t')){
            if (str.charAt(pos) == '\n'){
                line++;
                linePos = 0;
            }
            linePos++;
            pos++;
        }
        if (pos >= str.length()){
            return new Token(line, linePos, TokenType.END);
        }
        if (str.charAt(pos) == '('){
            pos++;
            linePos++;
            return new Token(line, linePos, TokenType.ROUND_BR_OPEN);
        }
        if (str.charAt(pos) == ')'){
            pos++;
            linePos++;
            return new Token(line, linePos, TokenType.ROUND_BR_CLOSE);
        }
        if (str.charAt(pos) == '{'){
            pos++;
            linePos++;
            return new Token(line, linePos, TokenType.CURLY_BR_OPEN);
        }
        if (str.charAt(pos) == '}'){
            pos++;
            linePos++;
            return new Token(line, linePos, TokenType.CURLY_BR_CLOSE);
        }
        if (str.charAt(pos) == ';'){
            pos++;
            linePos++;
            return new Token(line, linePos, TokenType.SEMICOLON);
        }
        if ((str.charAt(pos) >= 'a' && str.charAt(pos) <= 'z') || (str.charAt(pos) >= 'A' && str.charAt(pos) <= 'Z')){
            int len = 0;
            while (pos < str.length() && ((str.charAt(pos) >= 'a' && str.charAt(pos) <= 'z') || (str.charAt(pos) >= 'A' && str.charAt(pos) <= 'Z') || (str.charAt(pos) >= '0' && str.charAt(pos) <= '9'))){
                pos++;
                linePos++;
                len++;
            }
            String name = str.substring(pos-len, pos);
            switch (name){
                case "if":
                    return new Token(line, linePos, TokenType.IF);
                case "else":
                    return new Token(line, linePos, TokenType.ELSE);
                case "repeat":
                    return new Token(line, linePos, TokenType.REPEAT);
                case "while":
                    return new Token(line, linePos, TokenType.WHILE);
                case "function":
                    return new Token(line, linePos, TokenType.FUNCTION);
                default:
                    return new Token(line, linePos, name);
            }
        }
        if (str.charAt(pos) >= '0' && str.charAt(pos) <= '9'){
            int len = 0;
            while (pos < str.length() && str.charAt(pos) >= '0' && str.charAt(pos) <= '9'){
                pos++;
                linePos++;
                len++;
            }
            String value = str.substring(pos-len, pos);
            return new Token(line, linePos, Integer.parseInt(value));
        }
        throw new UnexpectedTokenException(new Token(line, linePos, TokenType.UNEXPECTED, str.substring(pos, pos+1)));
    }

    public int getLine() {
        return line;
    }

    public int getLinePos() {
        return linePos;
    }

    public void prevToken(){
        pos = prevPos;
        line = prevLine;
        linePos = prevLinePos;
    }

    public String getStr() {
        return str;
    }

    public int getPos() {
        return pos;
    }
}
