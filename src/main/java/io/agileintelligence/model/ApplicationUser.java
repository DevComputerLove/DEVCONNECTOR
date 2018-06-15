package io.agileintelligence.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Entity
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 2, max = 30, message = "From Java Server - Name must be between 2 and 30 characters")
    private String name;

    @Email(message = "Email is Invalid")
    @NotBlank(message = "Email field is required")
    private String email;


    //@Min(value = 2, message = "Password must be at least 6 characters")
    @NotBlank(message = "Password field is required")
    //@Size(min=6, max = 30, message = "Password must be at least 6 characters")
    private String password;

    //@NotBlank(message = "Confirm Password field is required")
    @Transient
    private String password2;

    private String avatar;

    @Column(updatable = false)
    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    public ApplicationUser() {
    }

    public ApplicationUser(ApplicationUser applicationUser) {
    }

    public ApplicationUser(@Size(min = 2, max = 30, message = "From Java Server - Name must be between 2 and 30 characters") String name, @Email(message = "Email is Invalid") @NotBlank(message = "Email field is required") String email, @NotBlank(message = "Password field is required") String password,  String avatar) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @PrePersist
    protected void onCreate(){this.date = new Date();}
}

