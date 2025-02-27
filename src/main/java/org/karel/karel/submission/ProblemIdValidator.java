package org.karel.karel.submission;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.karel.karel.problem.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

public class ProblemIdValidator implements ConstraintValidator<ValidProblemId, Integer> {

    @Autowired
    ProblemRepository problemRepository;

    @Override
    public void initialize(ValidProblemId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        try {
            problemRepository.getProblem(value);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
