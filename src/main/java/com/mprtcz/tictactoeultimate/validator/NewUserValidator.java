package com.mprtcz.tictactoeultimate.validator;

import com.mprtcz.tictactoeultimate.model.User;
import com.mprtcz.tictactoeultimate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Azet on 2016-11-25.
 */
@Component
public class NewUserValidator {

    private final
    UserService userService;

    @Autowired
    public NewUserValidator(UserService userService) {
        this.userService = userService;
    }

    public List<UserConstraintViolation> validateUser(User user) {
        List<UserConstraintViolation> violations = new ArrayList<>();
        violations.addAll(UserConstraintViolation.convertViolations(getHibernateConstraintViolations(user)));
        violations.addAll(validateNewUser(user));
        return violations;
    }

    private Set<ConstraintViolation<User>> getHibernateConstraintViolations(User user) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(user);
    }

    public List<UserConstraintViolation> validateNewUser(User newUser) {
        List<UserConstraintViolation> userConstraintViolations = new ArrayList<>();
        if(checkIfSsoIdExists(newUser)) {
            userConstraintViolations.add(new UserConstraintViolation("SsoId", "SsoId already exists"));
        }
        if(checkIfEmailExists(newUser)) {
            userConstraintViolations.add(new UserConstraintViolation("Email", "EMail already exists"));
        }
        return userConstraintViolations;
    }

    private boolean checkIfSsoIdExists(User newUser) {
        for(User u : this.userService.getAllUsers()) {
            if(u.getSsoId().equals(newUser.getSsoId())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfEmailExists(User newUser) {
        for(User u : this.userService.getAllUsers()) {
            if(u.getEmail().equals(newUser.getEmail())) {
                return true;
            }
        }
        return false;
    }
}
