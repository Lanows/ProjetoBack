package projeto.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import projeto.infrastructure.IObjectPersistent;
import projeto.utils.GenderSituation;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "tb_person")
public class Person implements IObjectPersistent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cl_id_TMDB")
    private int idTMDB;

    @Size(min = 2, max = 50)
    @Column(name = "cl_name")
    private String name;

    @Column(name = "cl_height", length = 3)
    private double height;

    @Column(name = "cl_hometown")
    private String hometown;

    @Column(name = "cl_country")
    private String country;

    @Column(name = "cl_profile_Path")
    private String profilePath;

    @Enumerated
    @Column(name = "cl_gender")
    private GenderSituation gender;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Participation.class, mappedBy = "person")
    private Set<Participation> participations;

    public Person() {
    }

    public Person(Long id, int idTMDB, @Size(min = 2, max = 50) String name, double height, String hometown, String country, String profilePath, GenderSituation gender, Set<Participation> participations) {
        this.id = id;
        this.idTMDB = idTMDB;
        this.name = name;
        this.height = height;
        this.hometown = hometown;
        this.country = country;
        this.profilePath = profilePath;
        this.gender = gender;
        this.participations = participations;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public GenderSituation getGender() {
        return gender;
    }

    public void setGender(GenderSituation gender) {
        this.gender = gender;
    }

    public int getIdTMDB() {
        return idTMDB;
    }

    public void setIdTMDB(int idTMDB) {
        this.idTMDB = idTMDB;
    }

    public Set<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
