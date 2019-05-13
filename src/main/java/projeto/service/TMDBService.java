package projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import projeto.utils.*;

@Service
public class TMDBService {


    @Autowired
    @Qualifier("api.rest.tmbd")
    private WebClient tmdbWebClient;

    public <T> T makeGetRequest(String path, Class<T> returnTypeClass, MultiValueMap<String, String> params){
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {}

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        if (params != null){
            queryParams.addAll(params);
        }

        queryParams.add("api_key", "00e3115d6f7301d438db08a861c31c32");

        return tmdbWebClient
                .get()
                .uri(builder -> builder
                        .path(path)
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(returnTypeClass)
                .block();
    }

    public <T> T makeGetRequest(String path, Class<T> returnTypeClass){
        return makeGetRequest(path, returnTypeClass, null);
    }

    public MovieDetails getMovieById(int id){
        return makeGetRequest("/movie/" + id, MovieDetails.class);
    }

    public TvSeriesDetails getTvSeriesById(int id){
        return makeGetRequest("/tv/" + id, TvSeriesDetails.class);
    }

    public MoviesList getTrendingMoviesOfTheWeek(int page){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", Integer.toString(page));

        return makeGetRequest("/trending/movie/week", MoviesList.class, params);
    }

    public TvSeriesList getTrendingTvOfTheWeek(int page){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", Integer.toString(page));

        return makeGetRequest("/trending/tv/week", TvSeriesList.class, params);
    }

    public ProgramCredits getMovieCreditsById(int id){
        return makeGetRequest("/movie/" + id + "/credits", ProgramCredits.class);
    }

    public ProgramCredits getTvCreditsById(int id){
        return makeGetRequest("/tv/" + id + "/credits", ProgramCredits.class);
    }

    public PersonDetails getPersonDetailsById(int id){
        return makeGetRequest("/person/" + id, PersonDetails.class);
    }
}
