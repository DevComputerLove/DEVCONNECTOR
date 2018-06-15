package io.agileintelligence.repository;

import io.agileintelligence.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    //ApplicationUser findByUsername(String username);

    ApplicationUser findByEmail(String email);
}
