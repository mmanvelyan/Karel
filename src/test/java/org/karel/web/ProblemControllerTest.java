package org.karel.web;

import org.karel.karel.Main;
import org.karel.karel.problem.Problem;
import org.karel.karel.problem.ProblemController;
import org.karel.karel.problem.ProblemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProblemController.class)
@ContextConfiguration(classes = Main.class)
public class ProblemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProblemRepository problemRepository;

    @Test
    void problemList() throws Exception {
        String problemName = "testTitle";
        String problemDescription = "testDescription";
        List<Problem> problems = List.of(
                new Problem(1, problemName, problemDescription)
        );
        when(problemRepository.getProblems()).thenReturn(problems);
        mockMvc.perform(get("/problems"))
                .andExpect(status().isOk())
                .andExpect(view().name("problems"))
                .andExpect(model().attribute("problems", equalTo(problems)))
                .andExpect(content().string(containsString(problemName)));
    }

    @Test
    void problemById() throws Exception {
        String problemTitle = "testTitle";
        String problemDescription = "testDescription";
        Problem problem = new Problem(1, problemTitle, problemDescription);
        when(problemRepository.getProblem(1)).thenReturn(problem);
        mockMvc.perform(get("/problems/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("problemTask"))
                .andExpect(model().attribute("problem", equalTo(problem)))
                .andExpect(content().string(containsString(problemDescription)));
    }

}
