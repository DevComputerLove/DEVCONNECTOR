package io.agileintelligence.model.ProfileClasses;

import io.agileintelligence.model.Profile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String school;

    @NotBlank
    private String degree;

    @NotBlank
    private String fieldofstudy;

    @NotBlank
    private Date from;

    private Date to;

    private Boolean current = false;

    private String description;

    @ManyToOne
    private Profile profile;

    public Education() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFieldofstudy() {
        return fieldofstudy;
    }

    public void setFieldofstudy(String fieldofstudy) {
        this.fieldofstudy = fieldofstudy;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
