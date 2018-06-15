package io.agileintelligence.Validator;

import io.agileintelligence.model.ApplicationUser;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ApplicationUser.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        ApplicationUser user = (ApplicationUser)object;

        if(user.getPassword().length() < 6){
            errors.rejectValue("password", "Length","Password must be at least 6 characters");

        }

        if(!user.getPassword().equals(user.getPassword2())){
            errors.rejectValue("password2", "Match","Passwords Must Match");
        }

    }
}
