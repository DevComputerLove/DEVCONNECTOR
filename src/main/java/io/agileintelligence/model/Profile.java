package io.agileintelligence.model;

import io.agileintelligence.model.ProfileClasses.Education;
import io.agileintelligence.model.ProfileClasses.Experience;
import io.agileintelligence.model.ProfileClasses.Social;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
public class Profile {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Profile handle is required")
    @Max(40)
    private String handle;
    private String company;

    private String website;
    private String location;

    @NotBlank(message = "Profile status is required")
    private String status;

    @NotBlank(message = "Profile skills are required")
    @Column(name="skills")
    @ElementCollection(targetClass = String.class)
    private List<String> skills = new ArrayList<>();

    private String bio;

    private String githubusername;

    private Date date;

    @OneToOne
    private ApplicationUser applicationUser;

    @OneToOne(cascade = CascadeType.ALL)
    private Social social;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profile")
    private List<Education> educations = new ArrayList<>();

    public Profile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGithubusername() {
        return githubusername;
    }

    public void setGithubusername(String githubusername) {
        this.githubusername = githubusername;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public Social getSocial() {
        return social;
    }

    public void setSocial(Social social) {
        this.social = social;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    @PrePersist
    protected void onCreate(){this.date = new Date();}
}
