package org.karel.karel.submission;

import org.karel.karel.tester.Status;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class SubmissionJdbcRepository implements SubmissionRepository {

    private final JdbcClient jdbcClient;
    private final RowMapper<Submission> submissionRowMapper = (rs, rowNum) ->
            new Submission(rs.getInt("submission_id"),
                    rs.getInt("user_id"),
                    rs.getInt("problem_id"),
                    rs.getString("code"),
                    Status.values()[rs.getInt("status_id")]);

    public SubmissionJdbcRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Submission getById(int id) {
        String query = "select submission_id, user_id, problem_id, code, status_id from karel.submissions where submission_id = :id";
        return jdbcClient
                .sql(query)
                .param("id", id)
                .query(submissionRowMapper)
                .single();
    }

    @Override
    public Integer save(Submission submission) {
        String update = "insert into karel.submissions(user_id, problem_id, code, status_id) " +
                        "values(:user_id, :problem_id, :code, :status_id)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Integer rowsAffected = jdbcClient
                        .sql(update)
                        .param("user_id", submission.getUser_id())
                        .param("problem_id", submission.getProblem_id())
                        .param("code", submission.getCode())
                        .param("status_id", submission.getStatus().ordinal())
                        .update(keyHolder);
        return (Integer) Objects.requireNonNull(keyHolder.getKeys()).get("submission_id");
    }

    public void updateStatus(int submission_id, Status status){
        String update = "update karel.submissions " +
                        "set status_id = :status_id where submission_id = :submission_id";
        jdbcClient
                .sql(update)
                .param("status_id", status.ordinal())
                .param("submission_id", submission_id)
                .update();
    }
}
