package org.karel.web;

import org.junit.jupiter.api.Test;
import org.karel.karel.Main;
import org.karel.karel.submission.Submission;
import org.karel.karel.submission.SubmissionService;
import org.karel.karel.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.karel.karel.tester.Status.ACCEPTED;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SubmissionServiceTest extends RepositoryTest {

    @Autowired
    private SubmissionService submissionService;
    @MockBean
    private UserService userService;

    @Test
    public void createAndGet(){
        int id = submissionService.createSubmission(1, "function main(){ move(); }");
        when(userService.getCurrentUserId()).thenReturn(0);
        Submission submission = submissionService.getById(id);
        assertEquals(id, submission.getSubmission_id());
        assertEquals(0, submission.getUser_id());
        assertEquals(1, submission.getProblem_id());
        assertEquals("function main(){ move(); }", submission.getCode());
        assertEquals(ACCEPTED, submission.getStatus());
    }
}














