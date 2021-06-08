package com.sachet.userservice.validators;

import com.sachet.userservice.custom_constraints.UniqueEmail;
import com.sachet.userservice.entity.User;
import com.sachet.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String > {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepository.findByEmail(s);
        return user == null;
    }
}
