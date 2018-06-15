package io.agileintelligence.model.ProfileClasses;

import io.agileintelligence.model.Profile;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Social {

    @Id
    @GeneratedValue
    private Long id;
    @URL(message = "not a valid URL")
    private String youtube;
    @URL(message = "not a valid URL")
    private String twitter;
    @URL(message = "not a valid URL")
    private String facebook;
    @URL(message = "not a valid URL")
    private String linkedin;
    @URL(message = "not a valid URL")
    private String instagram;

    @OneToOne
    private Profile profile;

    public Social() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
