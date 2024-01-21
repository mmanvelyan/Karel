package org.example;

public class Lexer {
    private final String str;
    private int pos;

    public Lexer(String str, int pos){
        this.str = str;
        this.pos = pos;
    }

    public Lexer(String str){
        this(str, 0);
    }

    public Token nextToken(){
        while (pos < str.length() && (str.charAt(pos) == ' ' || str.charAt(pos) == '\n')){
            pos++;
        }
        if (pos >= str.length()){
            return new Token(TokenType.END);
        }
        if (str.charAt(pos) == '('){
            pos++;
            return new Token(TokenType.ROUND_BR_OPEN);
        }
        if (str.charAt(pos) == ')'){
            pos++;
            return new Token(TokenType.ROUND_BR_CLOSE);
        }
        if (str.charAt(pos) == '{'){
            pos++;
            return new Token(TokenType.CURLY_BR_OPEN);
        }
        if (str.charAt(pos) == '}'){
            pos++;
            return new Token(TokenType.CURLY_BR_CLOSE);
        }
        if ((str.charAt(pos) >= 'a' && str.charAt(pos) <= 'z') || (str.charAt(pos) >= 'A' && str.charAt(pos) <= 'Z')){
            int len = 0;
            while (pos < str.length() && ((str.charAt(pos) >= 'a' && str.charAt(pos) <= 'z') || (str.charAt(pos) >= 'A' && str.charAt(pos) <= 'Z') || (str.charAt(pos) >= '0' && str.charAt(pos) <= '9'))){
                pos++;
                len++;
            }
            String name = str.substring(pos, pos+len);
            switch (name){
                case "if":
                    return new Token(TokenType.IF);
                case "else":
                    return new Token(TokenType.ELSE);
                case "repeat":
                    return new Token(TokenType.REPEAT);
                case "while":
                    return new Token(TokenType.WHILE);
                case "function":
                    return new Token(TokenType.FUNCTION);
                default:
                    return new Token(name);
            }
        }
        if (str.charAt(pos) >= '0' && str.charAt(pos) <= '9'){
            int len = 0;
            while (pos < str.length() && str.charAt(pos) >= '0' && str.charAt(pos) <= '9'){
                pos++;
                len++;
            }
            String value = str.substring(pos, pos+len);
            return new Token(Integer.parseInt(value));
        }
        throw new UnexpectedTokenException(pos, new Token(TokenType.UNEXPECTED));
    }

}
