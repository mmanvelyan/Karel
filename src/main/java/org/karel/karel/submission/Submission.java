package org.karel.karel.submission;

import org.karel.karel.tester.Status;

public class Submission {

    private int submission_id;
    private int user_id;
    private int problem_id;
    private String code;
    private Status status;

    public Submission (int submission_id, int user_id, int problem_id, String code, Status status) {
        this.submission_id = submission_id;
        this.user_id = user_id;
        this.problem_id = problem_id;
        this.code = code;
        this.status = status;
    }

    public Submission (int user_id, int problem_id, String code, Status status){
        this(0, user_id, problem_id, code,status);
    }

    public Submission (int problem_id, String code, Status status){
        this(0,0, problem_id, code, status);
    }

    public Submission (int problem_id, String code){
        this(0,0, problem_id, code, Status.IN_QUEUE);
    }

    public Submission(){
        this(0, 0, 0, "", Status.IN_QUEUE);
    }

    public int getSubmission_id() {
        return submission_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getProblem_id() {
        return problem_id;
    }

    public String getCode() {
        return code;
    }

    public Status getStatus() {
        return status;
    }

    public void setSubmission_id(int submission_id) {
        this.submission_id = submission_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setProblem_id(int problem_id) {
        this.problem_id = problem_id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
