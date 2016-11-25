package com.mprtcz.tictactoeultimate.validator;

import com.mprtcz.tictactoeultimate.model.User;
import lombok.Getter;
import lombok.ToString;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Azet on 2016-11-25.
 */
@ToString
@Getter
public class UserConstraintViolation {
    private String property;
    String message ;

    public UserConstraintViolation(String property, String message) {
        this.property = property;
        this.message = message;
    }

    public UserConstraintViolation(ConstraintViolation<User> constraintViolation) {
        this.property = constraintViolation.getPropertyPath().toString();
        this.message = constraintViolation.getMessage();
    }

    public static List<UserConstraintViolation> convertViolations(Set<ConstraintViolation<User>> userConstraintViolationSet) {
        return userConstraintViolationSet.stream().map(UserConstraintViolation::new).collect(Collectors.toList());
    }
}
