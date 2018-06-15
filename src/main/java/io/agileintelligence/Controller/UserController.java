package io.agileintelligence.Controller;


import io.agileintelligence.Services.ApplicationUserService;
import io.agileintelligence.Validator.UserValidator;
import io.agileintelligence.config.JWTAuthenticationFilter;
import io.agileintelligence.model.ApplicationUser;
import io.agileintelligence.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/

@RestController
@RequestMapping("/api/users")
public class UserController {


    private ApplicationUserService applicationUserService;
    private UserValidator userValidator;

    AuthenticationManager authenticationManager;

    public UserController(ApplicationUserService applicationUserService, UserValidator userValidator
            ) {
        this.applicationUserService = applicationUserService;
        this.userValidator = userValidator;
    }



    @PostMapping("/register")
    public ResponseEntity<?> signUp(@Valid @RequestBody ApplicationUser user, BindingResult result){

        userValidator.validate(user,result);
        if(result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ApplicationUser>(applicationUserService.save(user),HttpStatus.CREATED);
    }
}
