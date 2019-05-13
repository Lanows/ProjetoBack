package projeto.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TvSeriesList {

    private int page;

    @JsonProperty("results")
    private List<TvSeriesDetails> tvSeriesDetailsList;

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

    public List<TvSeriesDetails> getTvSeriesDetailsList() {
        return tvSeriesDetailsList;
    }

    public void setTvSeriesDetailsList(List<TvSeriesDetails> tvSeriesDetailsList) {
        this.tvSeriesDetailsList = tvSeriesDetailsList;
    }
}
