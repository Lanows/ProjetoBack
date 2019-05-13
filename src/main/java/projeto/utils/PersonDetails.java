package projeto.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDetails {

    private int id;
    private int gender;
    private String name;

    @JsonProperty("profile_path")
    private String profilePath;
    @JsonProperty("place_of_birth")
    private String placeOfBirth;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDetails that = (PersonDetails) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
}
