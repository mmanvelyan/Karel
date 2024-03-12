package org.example;


/*
<program> ->                <function> <program> |
                            <function>

<function> ->               function <name> () { <body> }

<body> ->                   <statement> <body> |
                            ε

<statement> ->              <operation statement> |
                            <repeat statement> |
                            <while statement> |
                            <if statement>

<operation statement> ->    move(); |
                            turnLeft(); |
                            pickBeeper(); |
                            putBeeper(); |
                            <function call>

<function call> ->          <name> () ;

<repeat statement> ->       repeat ( <number> ) { <body> }

<while statement> ->        while ( <condition> ) { <body> }

<if statement> ->           if ( <condition> ) { <body> } |
                            if ( <condition> ) { <body> } else { <body> }

<condition> ->              frontIsClear() |
                            frontIsBlocked() |
                            leftIsClear() |
                            leftIsBlocked() |
                            rightIsClear() |
                            rightIsBlocked() |
                            beepersPresent() |
                            noBeepersPresent() |
                            beepersInBag() |
                            noBeepersInBag() |
                            facingNorth() |
                            notFacingNorth() |
                            facingEast() |
                            notFacingEast() |
                            facingSouth() |
                            notFacingSouth() |
                            facingWest() |
                            notFacingWest()
 */

import static org.example.TokenType.END;
import static org.example.TokenType.ROUND_BR_OPEN;

public class Parser {
    public Condition parseCondition(Lexer lex){
        Token next = lex.nextToken();
        if (next.getType() != TokenType.NAME){
            throw new UnexpectedTokenException(next, "Condition name");
        }
        Token nameToken = next;
        String name = next.getName();
        next = lex.nextToken();
        if (next.getType() != TokenType.ROUND_BR_OPEN){
            throw new UnexpectedTokenException(next, "(");
        }
        next = lex.nextToken();
        if (next.getType() != TokenType.ROUND_BR_CLOSE){
            throw new UnexpectedTokenException(next, ")");
        }
        switch (name){
            case "frontIsClear":
                return new Condition(ConditionType.FRONT_IS_CLEAR);
            case "frontIsBlocked":
                return new Condition(ConditionType.FRONT_IS_BLOCKED);
            case "leftIsClear":
                return new Condition(ConditionType.LEFT_IS_CLEAR);
            case "leftIsBlocked":
                return new Condition(ConditionType.LEFT_IS_BLOCKED);
            case "rightIsClear":
                return new Condition(ConditionType.RIGHT_IS_CLEAR);
            case "rightIsBlocked":
                return new Condition(ConditionType.RIGHT_IS_BLOCKED);
            case "beepersPresent":
                return new Condition(ConditionType.BEEPERS_PRESENT);
            case "noBeepersPresent":
                return new Condition(ConditionType.NO_BEEPERS_PRESENT);
            case "beepersInBag":
                return new Condition(ConditionType.BEEPERS_IN_BAG);
            case "noBeepersInBag":
                return new Condition(ConditionType.NO_BEEPERS_IN_BAG);
            case "facingNorth":
                return new Condition(ConditionType.FACING_NORTH);
            case "notFacingNorth":
                return new Condition(ConditionType.NOT_FACING_NORTH);
            case "facingEast":
                return new Condition(ConditionType.FACING_EAST);
            case "notFacingEast":
                return new Condition(ConditionType.NOT_FACING_EAST);
            case "facingSouth":
                return new Condition(ConditionType.FACING_SOUTH);
            case "notFacingSouth":
                return new Condition(ConditionType.NOT_FACING_SOUTH);
            case "facingWest":
                return new Condition(ConditionType.FACING_WEST);
            case "notFacingWest":
                return new Condition(ConditionType.NOT_FACING_WEST);
            default:
                throw new UnexpectedTokenException(nameToken, "Condition name");
        }
    }

    public Node parseBody(Lexer lex){
        Token next = lex.nextToken();
        if (next.getType() == TokenType.REPEAT){
            next = lex.nextToken();
            if (next.getType() != TokenType.ROUND_BR_OPEN){
                throw new UnexpectedTokenException(next, "(");
            }
            next = lex.nextToken();
            if (next.getType() != TokenType.NUMBER){
                throw new UnexpectedTokenException(next, "number");
            }
            int count = next.getValue();
            next = lex.nextToken();
            if (next.getType() != TokenType.ROUND_BR_CLOSE){
                throw new UnexpectedTokenException(next, ")");
            }
            next = lex.nextToken();
            if (next.getType() != TokenType.CURLY_BR_OPEN){
                throw new UnexpectedTokenException(next, "{");
            }
            Node body = parseBody(lex);
            next = lex.nextToken();
            if (next.getType() != TokenType.CURLY_BR_CLOSE){
                throw new UnexpectedTokenException(next, "}");
            }
            return new RepeatNode(count, body, parseBody(lex));
        } else if (next.getType() == TokenType.WHILE){
            next = lex.nextToken();
            if (next.getType() != TokenType.ROUND_BR_OPEN){
                throw new UnexpectedTokenException(next, "(");
            }
            Condition cond = parseCondition(lex);
            next = lex.nextToken();
            if (next.getType() != TokenType.ROUND_BR_CLOSE){
                throw new UnexpectedTokenException(next, ")");
            }
            next = lex.nextToken();
            if (next.getType() != TokenType.CURLY_BR_OPEN){
                throw new UnexpectedTokenException(next, "{");
            }
            Node body = parseBody(lex);
            next = lex.nextToken();
            if (next.getType() != TokenType.CURLY_BR_CLOSE){
                throw new UnexpectedTokenException(next, "}");
            }
            return new WhileNode(cond, body, parseBody(lex));
        } else if (next.getType() == TokenType.IF){
            next = lex.nextToken();
            if (next.getType() != TokenType.ROUND_BR_OPEN){
                throw new UnexpectedTokenException(next, "(");
            }
            Condition cond = parseCondition(lex);
            next = lex.nextToken();
            if (next.getType() != TokenType.ROUND_BR_CLOSE){
                throw new UnexpectedTokenException(next, ")");
            }
            next = lex.nextToken();
            if (next.getType() != TokenType.CURLY_BR_OPEN){
                throw new UnexpectedTokenException(next, "{");
            }
            Node body = parseBody(lex);
            next = lex.nextToken();
            if (next.getType() != TokenType.CURLY_BR_CLOSE){
                throw new UnexpectedTokenException(next, "}");
            }
            next = lex.nextToken();
            if (next.getType() != TokenType.ELSE) {
                lex.prevToken();
                return new IfNode(cond, body, new EmptyNode(), parseBody(lex));
            } else {
                next = lex.nextToken();
                if (next.getType() != TokenType.CURLY_BR_OPEN){
                    throw new UnexpectedTokenException(next, "{");
                }
                Node bodyElse = parseBody(lex);
                next = lex.nextToken();
                if (next.getType() != TokenType.CURLY_BR_CLOSE){
                    throw new UnexpectedTokenException(next, "}");
                }
                return new IfNode(cond, body, bodyElse, parseBody(lex));
            }
        } else {
            if (next.getType() != TokenType.NAME){
                lex.prevToken();
                return new EmptyNode();
            }
            String name = next.getName();
            next = lex.nextToken();
            if (next.getType() != TokenType.ROUND_BR_OPEN){
                throw new UnexpectedTokenException(next, "(");
            }
            next = lex.nextToken();
            if (next.getType() != TokenType.ROUND_BR_CLOSE) {
                throw new UnexpectedTokenException(next, ")");
            }
            next = lex.nextToken();
            if (next.getType() != TokenType.SEMICOLON) {
                throw new UnexpectedTokenException(next, ";");
            }
            switch (name){
                case "move":
                    return new OperationNode(Operation.MOVE, parseBody(lex));
                case "turnLeft":
                    return new OperationNode(Operation.TURN_LEFT, parseBody(lex));
                case "putBeeper":
                    return new OperationNode(Operation.PUT_BEEPER, parseBody(lex));
                case "pickBeeper":
                    return new OperationNode(Operation.PICK_BEEPER, parseBody(lex));
                default:
                    return new FunctionCallNode(name, parseBody(lex));
            }
        }
    }

    public void parse(Lexer lex, Functions funcs){
        Token next = lex.nextToken();
        while (next.getType() == TokenType.FUNCTION){
            next = lex.nextToken();
            if (next.getType() != TokenType.NAME){
                throw new UnexpectedTokenException(next, "Name");
            }
            String name = next.getName();
            next = lex.nextToken();
            if (next.getType() != TokenType.ROUND_BR_OPEN){
                throw new UnexpectedTokenException(next, "(");
            }
            next = lex.nextToken();
            if (next.getType() != TokenType.ROUND_BR_CLOSE) {
                throw new UnexpectedTokenException(next, ")");
            }
            next = lex.nextToken();
            if (next.getType() != TokenType.CURLY_BR_OPEN) {
                throw new UnexpectedTokenException(next, "{");
            }
            Node body = parseBody(lex);
            next = lex.nextToken();
            if (next.getType() != TokenType.CURLY_BR_CLOSE) {
                throw new UnexpectedTokenException(next, "}");
            }
            funcs.addFunction(name, body);
            next = lex.nextToken();
        }
        if (next.getType() != TokenType.END){
            throw new UnexpectedTokenException(next, "function or end");
        }
    }
}



















