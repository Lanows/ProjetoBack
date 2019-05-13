package projeto.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import projeto.infrastructure.IObjectPersistent;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Program implements IObjectPersistent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cl_idTMDB")
    private Long idTMDB;

    @Column(name = "cl_backdrop_Path")
    private String backdropPath;

    @Size(min = 2, max = 50)
    @Column(name = "cl_title")
    private String title;

    @Size(min = 1, max = 1500)
    @Column(name = "cl_overview")
    private String overview;

    @Size(min = 1, max = 50)
    @Column(name = "cl_country")
    private String country;

    @Size(min = 1, max = 30)
    @Column(name = "cl_language")
    private String language;

    @Temporal(TemporalType.DATE)
    @Column(name = "cl_release_Date")
    private Date releaseDate;


    @Column(name = "cl_runtime")
    private int runtime;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "tb_program_genre_pk",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "cl_genre_group_from_program")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "cl_genre_type_from_genre")})
    private Set<Genre> genres;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Participation.class, mappedBy = "program")
    private Set<Participation> participations;

    public Program() {
    }

    public Program(Long id, Long idTMDB, String backdropPath, @Size(min = 2, max = 50) String title, @Size(min = 1, max = 1500) String overview, @Size(min = 1, max = 30) String country, @Size(min = 1, max = 30) String language, Date releaseDate, int runtime, Set<Genre> genres, Set<Participation> participations) {
        this.id = id;
        this.idTMDB = idTMDB;
        this.backdropPath = backdropPath;
        this.title = title;
        this.overview = overview;
        this.country = country;
        this.language = language;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.genres = genres;
        this.participations = participations;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Set<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTMDB() {
        return idTMDB;
    }

    public void setIdTMDB(Long idTMDB) {
        this.idTMDB = idTMDB;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

}
