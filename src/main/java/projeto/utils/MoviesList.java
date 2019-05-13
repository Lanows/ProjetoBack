package projeto.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MoviesList {

    private int page;

    @JsonProperty("results")
    private List<MovieDetails> moviesList;

    public String toString(){
        try{
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieDetails> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<MovieDetails> moviesList) {
        this.moviesList = moviesList;
    }
}
