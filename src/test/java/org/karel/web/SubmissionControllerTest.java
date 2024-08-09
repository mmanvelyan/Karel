package org.karel.web;

import org.junit.jupiter.api.Test;
import org.karel.karel.Main;
import org.karel.karel.submission.Submission;
import org.karel.karel.submission.SubmissionController;
import org.karel.karel.submission.SubmissionService;
import org.karel.karel.tester.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubmissionController.class)
@ContextConfiguration(classes = Main.class)
public class SubmissionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SubmissionService submissionService;

    @Test
    @WithMockUser
    public void getForm() throws Exception {
        mockMvc.perform(get("/submit"))
                .andExpect(status().isOk())
                .andExpect(view().name("submissionForm"))
                .andExpect(content().string(containsString("Submit")));
    }

    @Test
    @WithMockUser
    public void submitForm() throws Exception {
        Submission submission = new Submission(1, "#");
        when(submissionService.createSubmission(anyInt(), anyString())).thenReturn(1);
        mockMvc.perform(post("/submit")
                        .flashAttr("submission", submission)
                        .with(csrf()))
                .andExpect(redirectedUrl("/submission/1"));
    }

    @Test
    @WithMockUser
    public void submissionView() throws Exception {
        Submission submission = new Submission(1, 0, 1, "#", Status.ACCEPTED);
        when(submissionService.getById(1)).thenReturn(submission);
        mockMvc.perform(get("/submission/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("submissionView"))
                .andExpect(content().string(containsString("#")))
                .andExpect(content().string(containsString("ACCEPTED")));
    }

}












