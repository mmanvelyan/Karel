package org.karel.karel.compiler;


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

import org.karel.karel.Condition;
import org.karel.karel.Node;
import org.karel.karel.condition.*;
import org.karel.karel.karelmap.Direction;
import org.karel.karel.node.*;

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
        return switch (name) {
            case "frontIsClear" -> new NotCondition(new FrontIsBlockedCondition());
            case "frontIsBlocked" -> new FrontIsBlockedCondition();
            case "leftIsClear" -> new NotCondition(new LeftIsBlockedCondition());
            case "leftIsBlocked" -> new LeftIsBlockedCondition();
            case "rightIsClear" -> new NotCondition(new RightIsBlockedCondition());
            case "rightIsBlocked" -> new RightIsBlockedCondition();
            case "beepersPresent" -> new BeepersPresentCondition();
            case "noBeepersPresent" -> new NotCondition(new BeepersPresentCondition());
            case "beepersInBag" -> new BeepersInBagCondition();
            case "noBeepersInBag" -> new NotCondition(new BeepersInBagCondition());
            case "facingNorth" -> new FacingWallCondition(Direction.NORTH);
            case "notFacingNorth" -> new NotCondition(new FacingWallCondition(Direction.NORTH));
            case "facingEast" -> new FacingWallCondition(Direction.EAST);
            case "notFacingEast" -> new NotCondition(new FacingWallCondition(Direction.EAST));
            case "facingSouth" -> new FacingWallCondition(Direction.SOUTH);
            case "notFacingSouth" -> new NotCondition(new FacingWallCondition(Direction.SOUTH));
            case "facingWest" -> new FacingWallCondition(Direction.WEST);
            case "notFacingWest" -> new NotCondition(new FacingWallCondition(Direction.WEST));
            default -> throw new UnexpectedTokenException(nameToken, "Condition name");
        };
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
            return switch (name) {
                case "move" -> new OperationNode(Operation.MOVE, parseBody(lex));
                case "turnLeft" -> new OperationNode(Operation.TURN_LEFT, parseBody(lex));
                case "putBeeper" -> new OperationNode(Operation.PUT_BEEPER, parseBody(lex));
                case "pickBeeper" -> new OperationNode(Operation.PICK_BEEPER, parseBody(lex));
                default -> new FunctionCallNode(name, parseBody(lex));
            };
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



















