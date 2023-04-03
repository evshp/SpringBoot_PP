package com.springbootkata.kata.Util;



import com.springbootkata.kata.models.User;
import com.springbootkata.kata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {


    private final UserService userServiceJPA;


    @Autowired
    public UserValidator(UserService userServiceJPA) {
        this.userServiceJPA = userServiceJPA;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        User userFromDB1 = userServiceJPA.getUserById(user.getId());
        if (userFromDB1 == null) {
            userFromDB1 = new User();
            userFromDB1.setEmail("neverGetThisEmail");
        }





        if (user.getEmail() != null && !user.getEmail().equals(userFromDB1.getEmail())) {
            if (userServiceJPA.getUserByEmail(user.getEmail()).isPresent()) {
                errors.rejectValue("email",
                        "Duplicate.userForm.email",
                        "Пользователь с таким email уже существует");
            }
        }


    }


}
