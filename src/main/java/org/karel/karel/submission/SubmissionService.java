package org.karel.karel.submission;

import org.karel.karel.tester.Status;
import org.karel.karel.tester.Test;
import org.karel.karel.tester.TestRepository;
import org.karel.karel.tester.Tester;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {

    private final Tester tester;
    private final SubmissionRepository submissionRepository;
    private final TestRepository testRepository;

    public SubmissionService(Tester tester, SubmissionRepository submissionRepository, TestRepository testRepository) {
        this.tester = tester;
        this.submissionRepository = submissionRepository;
        this.testRepository = testRepository;
    }

    public Integer createSubmission(int problem_id, String code) {
        Submission submission = new Submission(problem_id, code, 0);
        int id = submissionRepository.save(submission);
        List<Test> tests = testRepository.getTests(problem_id);
        Status res = tester.testAll(code, tests);
        submissionRepository.updateStatus(id, res.ordinal());
        return id;
    }

    public Submission getById(int id) {
        return submissionRepository.getById(id);
    }
}
