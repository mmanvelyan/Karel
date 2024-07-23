package org.karel.karel.tester;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestJdbcRepository implements TestRepository {

    private final JdbcClient jdbcClient;
    private final RowMapper<Test> testRowMapper = (rs, rowNum) ->
            new Test(rs.getInt("test_id"),
                    rs.getInt("problem_id"),
                    rs.getString("input"),
                    rs.getString("output"));

    public TestJdbcRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Test> getTests(int problemId) {
        String query = "select test_id, problem_id, input, output from karel.tests where problem_id = :id";
        return jdbcClient
                .sql(query)
                .param("id", problemId)
                .query(testRowMapper)
                .list();
    }

}
