package org.karel.karel.problem;

import org.karel.karel.karelmap.KarelMap;
import org.karel.karel.submission.Submission;
import org.karel.karel.submission.SubmissionService;
import org.karel.karel.tester.Test;
import org.karel.karel.tester.TestJdbcRepository;
import org.karel.karel.tester.TestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProblemController {

    private final ProblemRepository problemRepository;
    private final TestRepository testRepository;
    private final SubmissionService submissionService;

    public ProblemController(ProblemRepository problemRepository, TestRepository testRepository, SubmissionService submissionService) {
        this.problemRepository = problemRepository;
        this.testRepository = testRepository;
        this.submissionService = submissionService;
    }

    @RequestMapping("/problems")
    public ModelAndView listProblems(){
        List<Problem> problems = problemRepository.getProblems();

        ModelAndView modelAndView = new ModelAndView("problems");
        modelAndView.addObject("problems", problems);
        return modelAndView;
    }

    @RequestMapping("/problems/{id}")
    public ModelAndView showProblem(@PathVariable int id){
        Problem problem = problemRepository.getProblem(id);
        Test test = testRepository.getTests(id).get(0);
        List<Submission> submissions = submissionService.getByUserAndProblem(id);
        ModelAndView modelAndView = new ModelAndView("problemTask");
        modelAndView.addObject("problem", problem);
        modelAndView.addObject("input", new KarelMap(test.input()).toString());
        modelAndView.addObject("output", new KarelMap(test.output()).toString());
        modelAndView.addObject("submissions", submissions);
        return modelAndView;
    }
}
