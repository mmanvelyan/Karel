package org.karel.karel.tester;

public record Test (
    int test_id,
    int problem_id,
    String input,
    String output
){}
