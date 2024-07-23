package org.karel.web;

import org.karel.karel.Main;
import org.karel.karel.problem.ProblemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Sql(scripts = {"file:src/main/sql/010.createSchema.sql", "file:src/test/resources/problems.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class ProblemRepositoryTest {

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
