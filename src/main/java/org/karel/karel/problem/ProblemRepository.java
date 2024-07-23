package org.karel.karel.problem;

import java.util.List;

public interface ProblemRepository {
    List<Problem> getProblems();
    Problem getProblem(int id);
}
