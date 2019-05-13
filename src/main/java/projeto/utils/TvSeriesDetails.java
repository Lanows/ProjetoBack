package projeto.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TvSeriesDetails {

    private int id;
    private String overview;
    private List<ProgramGenre> genres;
    private ProgramCredits programCredits;

    @JsonProperty("name")
    private String title;

    @JsonProperty("episode_run_time")
    private List<Integer> runtime;

    @JsonProperty("number_of_seasons")
    private int numberOfSeasons;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("origin_country")
    private List<String> country;

    @JsonProperty("original_language")
    private String language;

    @JsonProperty("first_air_date")
    private Date releaseDate;

    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Integer> getRuntime() {
        return runtime;
    }

    public void setRuntime(List<Integer> runtime) {
        this.runtime = runtime;
    }

    public List<ProgramGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<ProgramGenre> genres) {
        this.genres = genres;
    }

    public ProgramCredits getProgramCredits() {
        return programCredits;
    }

    public void setProgramCredits(ProgramCredits programCredits) {
        this.programCredits = programCredits;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(List<String> country) {
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
}
