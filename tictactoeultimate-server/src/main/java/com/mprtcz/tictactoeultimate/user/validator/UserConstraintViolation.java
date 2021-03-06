package com.mprtcz.tictactoeultimate.user.validator;

import com.mprtcz.tictactoeultimate.user.model.User;
import lombok.Getter;
import lombok.ToString;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by mprtcz on 2016-11-25.
 */
@ToString
@Getter
public class UserConstraintViolation {
    private String property;
    String message ;

    UserConstraintViolation(String property, String message) {
        this.property = property;
        this.message = message;
    }

    private UserConstraintViolation(ConstraintViolation<User> constraintViolation) {
        this.property = constraintViolation.getPropertyPath().toString();
        this.message = constraintViolation.getMessage();
    }

    static List<UserConstraintViolation> convertViolations(Set<ConstraintViolation<User>> userConstraintViolationSet) {
        return userConstraintViolationSet.stream().map(UserConstraintViolation::new).collect(Collectors.toList());
    }
}
