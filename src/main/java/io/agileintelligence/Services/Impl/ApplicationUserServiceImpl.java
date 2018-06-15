package io.agileintelligence.Services.Impl;

import io.agileintelligence.ExceptionHandling.UserAlreadyExistException;
import io.agileintelligence.Services.ApplicationUserService;
import io.agileintelligence.Utilities.MD5Util;
import io.agileintelligence.model.ApplicationUser;
import io.agileintelligence.repository.ApplicationUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private ApplicationUserRepository applicationUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ApplicationUser save(ApplicationUser user)  {

        ApplicationUser checkIfExists =  applicationUserRepository.findByEmail(user.getEmail());

        if(checkIfExists !=null){
            throw new UserAlreadyExistException("User already exists");
        }

        String hash = MD5Util.md5Hex(user.getEmail());
        user.setAvatar("//www.gravatar.com/avatar/"+hash+"?s=200&r=pg&d=mm");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPassword2("");
        return applicationUserRepository.save(user);
    }
}
