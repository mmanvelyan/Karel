package org.karel.web;

import org.karel.karel.Main;
import org.karel.karel.submission.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Sql()
public class SubmissionRepositoryTest {

    @Autowired
    private SubmissionRepository repo;



}
