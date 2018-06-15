package io.agileintelligence.config;

import io.agileintelligence.ExceptionHandling.InvalidLoginParametersException;
import io.agileintelligence.model.ApplicationUser;
import io.agileintelligence.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private ApplicationUserRepository applicationUserRepository;

    public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByEmail(username);


        if (applicationUser == null) {
            System.out.println("caught at the UserDetails checking user");

            throw new InvalidLoginParametersException(username);
        }


        System.out.println("caught at the UserDetails checking password");

        return applicationUser;
    }


}
