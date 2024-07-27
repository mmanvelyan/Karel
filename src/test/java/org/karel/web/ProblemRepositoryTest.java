package org.karel.web;

import org.karel.karel.problem.ProblemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProblemRepositoryTest extends RepositoryTest {

    @Autowired
    private ProblemRepository problemRepository;

    @Test
    public void listNotNull() {
        assertNotNull(problemRepository.getProblems());
    }

    @Test
    public void problemList() {
        assertEquals(3, problemRepository.getProblems().size());
    }

    @Test
    public void problemById() {
        assertEquals(1, problemRepository.getProblem(1).id());
        assertEquals("testTitle1", problemRepository.getProblem(1).title());
        assertEquals("testDescription1", problemRepository.getProblem(1).description());
    }

}
