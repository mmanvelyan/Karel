package org.karel.karel.submission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submissionForm(Model model) {
        model.addAttribute("submission", new Submission(1, "Your code here", 0));
        return "submissionForm";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(@ModelAttribute("submission") Submission s, Model model) {
        String code = s.getCode();
        int problem_id = s.getProblem_id();
        int id = submissionService.createSubmission(problem_id, code);
        return "redirect:/submission/" + id;
    }

    @RequestMapping("/submission/{id}")
    public String submissionView(@PathVariable int id, Model model){
        Submission submission = submissionService.getById(id);
        model.addAttribute("submission", submission);
        return "submissionView";
    }

}
