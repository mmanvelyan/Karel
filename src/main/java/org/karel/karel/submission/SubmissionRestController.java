package org.karel.karel.submission;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class SubmissionRestController {

    private final SubmissionService submissionService;

    public SubmissionRestController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/submit-rest/{problemId}")
    public Submission submit(@PathVariable("problemId") int problemId, @RequestBody String code) {
        int id = submissionService.createSubmission(problemId, code);
        return submissionService.getById(id);
    }

    @GetMapping("/submit-rest/submissions/{problemId}")
    public List<Submission> submissions(@PathVariable("problemId") int problemId) {
        return submissionService.getByUserAndProblem(problemId);
    }

}







