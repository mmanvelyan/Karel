package org.karel.web;

import org.karel.karel.Main;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Sql(scripts = {"file:src/main/sql/000.dropSchema.sql",
        "file:src/main/sql/010.createSchema.sql",
        "file:src/test/resources/users.sql",
        "file:src/main/sql/011.createStatuses.sql",
        "file:src/test/resources/problems.sql",
        "file:src/test/resources/submissions.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class RepositoryTest {

}
