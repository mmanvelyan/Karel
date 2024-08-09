package org.karel.karel.submission;

import org.karel.karel.tester.Status;

import java.util.List;

public interface SubmissionRepository {
    Submission getById(int id);
    Integer save(Submission submission);
    void updateStatus(int id, Status status);
    List<Submission> getByUser(int user_id);
    List<Submission> getByUserAndProblem(int user_id, int problem_id);
}
