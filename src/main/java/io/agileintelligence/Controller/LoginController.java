package io.agileintelligence.Controller;

import io.agileintelligence.ExceptionHandling.InvalidLoginParametersException;
import io.agileintelligence.config.UserDetailsServiceImpl;
import io.agileintelligence.model.ApplicationUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private UserDetailsServiceImpl userDetailsService;

    public LoginController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("")
    public ResponseEntity<String> loginUser(@RequestBody ApplicationUser user){

        return new ResponseEntity<String>("hit the login", HttpStatus.CREATED);

    }
}
