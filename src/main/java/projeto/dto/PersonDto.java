package projeto.dto;

import projeto.models.Participation;
import projeto.utils.GenderSituation;

import java.util.Set;

public class PersonDto {


    private String name;

    private double height;

    private String hometown;

    private String country;

    private String profilePath;

    private GenderSituation gender;

    private Set<Participation> participations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public GenderSituation getGender() {
        return gender;
    }

    public void setGender(GenderSituation gender) {
        this.gender = gender;
    }

    public Set<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }
}
