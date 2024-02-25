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

<operation statement> ->    <operation>

<repeat statement> ->       repeat ( <number> ) { <body> }

<while statement> ->        while ( <condition> ) { <body> }

<if statement> ->           if ( <condition> ) { <body> } |
                            if ( <condition> ) { <body> } else { <body> }

<operation> ->              move(); |
                            turnLeft(); |
                            pickBeeper(); |
                            putBeeper();

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

public class Parser {



}
