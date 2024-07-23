package org.karel.karel.submission;

import org.karel.karel.tester.Status;

public interface SubmissionRepository {
    Submission getById(int id);
    Integer save(Submission submission);
    void updateStatus(int id, int status_id);
}
