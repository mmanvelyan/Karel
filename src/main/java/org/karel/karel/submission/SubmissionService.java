package org.karel.karel.submission;

import org.karel.karel.tester.Status;
import org.karel.karel.tester.Test;
import org.karel.karel.tester.TestRepository;
import org.karel.karel.tester.Tester;
import org.karel.karel.user.UserRepository;
import org.karel.karel.user.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {

    private final Tester tester;
    private final SubmissionRepository submissionRepository;
    private final TestRepository testRepository;
    private final UserService userService;

    public SubmissionService(Tester tester, SubmissionRepository submissionRepository, TestRepository testRepository, UserService userService) {
        this.tester = tester;
        this.submissionRepository = submissionRepository;
        this.testRepository = testRepository;
        this.userService = userService;
    }

    public Integer createSubmission(int problem_id, String code) {
        Submission submission = new Submission(userService.getCurrentUserId(), problem_id, code, Status.IN_QUEUE);
        int id = submissionRepository.save(submission);
        List<Test> tests = testRepository.getTests(problem_id);
        Status res = tester.testAll(code, tests);
        submissionRepository.updateStatus(id, res);
        return id;
    }

    public Submission getById(int id) {
        Submission submission = submissionRepository.getById(id);
        if (submission.getUser_id() != userService.getCurrentUserId()) {
            throw new AccessDeniedException("Access denied");
        }
        return submissionRepository.getById(id);
    }

    public List<Submission> getByUserAndProblem(int problem_id) {
        if (userService.isAuthenticated()) {
            return submissionRepository.getByUserAndProblem(userService.getCurrentUserId(), problem_id);
        } else {
            return null;
        }
    }
}
