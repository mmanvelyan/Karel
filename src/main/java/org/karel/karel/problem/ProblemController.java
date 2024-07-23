package org.karel.karel.problem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProblemController {

    private final ProblemRepository problemRepository;

    public ProblemController(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
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

        ModelAndView modelAndView = new ModelAndView("problemTask");
        modelAndView.addObject("problem", problem);
        return modelAndView;
    }
}
