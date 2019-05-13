package projeto.dto;

import projeto.models.Genre;
import projeto.models.Participation;

import java.util.Date;
import java.util.Set;

public class MovieDto {

    private String backdropPath;

    private String title;

    private String overview;

    private String country;

    private String language;

    private Date releaseDate;

    private int runtime;

    private Set<Genre> genres;

    private Set<Participation> participations;

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

    public Set<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(Set<Participation> participations) {
        this.participations = participations;
    }
}
