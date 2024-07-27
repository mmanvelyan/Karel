package org.karel.web;

import org.junit.jupiter.api.Test;
import org.karel.karel.submission.Submission;
import org.karel.karel.submission.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubmissionRepositoryTest extends RepositoryTest {

    @Autowired
    private SubmissionRepository repo;

    @Test
    public void getById(){
        Submission submission = repo.getById(101);
        assertEquals(101, submission.getSubmission_id());
        assertEquals(0, submission.getUser_id());
        assertEquals(1, submission.getProblem_id());
        assertEquals("testCode1", submission.getCode());
        assertEquals(1, submission.getStatus_id());
    }

    @Test
    public void saveAndGet(){
        Submission submission = new Submission(0, 0, 1, "testCode0", 0);
        Integer id = repo.save(submission);
        submission = repo.getById(id);
        assertEquals(id, submission.getSubmission_id());
        assertEquals(0, submission.getUser_id());
        assertEquals(1, submission.getProblem_id());
        assertEquals("testCode0", submission.getCode());
        assertEquals(0, submission.getStatus_id());
    }

    @Test
    public void updateStatus(){
        repo.updateStatus(102, 5);
        Submission submission = repo.getById(102);
        assertEquals(5, submission.getStatus_id());
    }

}





















