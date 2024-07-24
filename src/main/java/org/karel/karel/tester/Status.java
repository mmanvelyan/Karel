package org.karel.karel.tester;

public enum Status {
    IN_QUEUE("IN QUEUE"),
    COMPILATION_ERROR("COMPILATION ERROR"),
    RUNTIME_ERROR("RUNTIME ERROR"),
    TIME_LIMIT_EXCEEDED("TIME LIMIT EXCEEDED"),
    WRONG_ANSWER("WRONG ANSWER"),
    ACCEPTED("ACCEPTED");

    private final String description;

    Status(String description){
        this.description = description;
    }

    public String toString(){
        return description;
    }
}
