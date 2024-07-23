package org.karel.karel.problem;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProblemJdbcRepository implements ProblemRepository {

    private final JdbcClient jdbcClient;

    private final RowMapper<Problem> problemRowMapper = (rs, rowNum) ->
            new Problem(rs.getInt("problem_id"),
                    rs.getString("title"),
                    rs.getString("description"));


    public ProblemJdbcRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Problem> getProblems() {
        String query = "select problem_id, title, description from karel.problems";

        return jdbcClient
                .sql(query)
                .query(problemRowMapper)
                .list();
    }

    @Override
    public Problem getProblem(int id) {
        String query = "select problem_id, title, description from karel.problems where problem_id = :id";
        return jdbcClient
                .sql(query)
                .param("id", id)
                .query(problemRowMapper)
                .single();
    }

}
