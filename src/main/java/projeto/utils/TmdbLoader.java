package projeto.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projeto.dao.*;
import projeto.models.*;
import projeto.service.TMDBService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TmdbLoader {

    public static final Logger logger = LoggerFactory.getLogger(TmdbLoader.class);

    @Autowired
    private IMovieDao movieDao;

    @Autowired
    private ITvSeriesDao tvSeriesDao;

    @Autowired
    private IGenreDao genreDao;

    @Autowired
    private IPersonDao personDao;

    @Autowired
    private IParticipationDao participationDao;

    @Autowired
    private TMDBService tmdbService;

    public void loadTmdbData() {
        this.fetchMovies();
        this.fetchTvSeries();
    }


    private void fetchMovies() {
        int minFetchedMovies = 25;
        int fetchedMovies = 0;
        int page = 1;
        int numberOfPrograms = 1;

        while(fetchedMovies < minFetchedMovies) {
            MoviesList moviesList = tmdbService.getTrendingMoviesOfTheWeek(page++);

            for (MovieDetails movieDetails: moviesList.getMoviesList()) {
                logger.info("Saving ------------------------------ Movie number: " + numberOfPrograms);
                MovieDetails completeMovieDetails = tmdbService.getMovieById(movieDetails.getId());
                this.saveMovie(completeMovieDetails);
                ProgramCredits programCredits = tmdbService.getMovieCreditsById(movieDetails.getId());
                fetchMoviesParticipation(programCredits, movieDetails);
                numberOfPrograms++;
            }

            fetchedMovies += moviesList.getMoviesList().size();
        }
    }

    @Transactional
    private void fetchMoviesParticipation(ProgramCredits programCredits, MovieDetails movieDetails){
        List<CastDetails> castDetailsList = programCredits.getCastDetailsList();
        List<CrewDetails> crewDetailsList = programCredits.getCrewDetailsList();

        int count = 0;
        final int kMaxFetchPersons = 10;

        for(CastDetails castDetails :castDetailsList){
            PersonDetails personDetails = tmdbService.getPersonDetailsById(castDetails.getPersonId());
            Person person = savePerson(personDetails);
            saveMovieParticipation(movieDetails, person, "Acting");

            if(count == kMaxFetchPersons){
                break;
            }
            count++;
        }
        count = 0;
        for(CrewDetails crewDetails :crewDetailsList){
            PersonDetails personDetails = tmdbService.getPersonDetailsById(crewDetails.getPersonId());
            Person person = savePerson(personDetails);
            saveMovieParticipation(movieDetails, person, crewDetails.getDepartment());

            if(count == kMaxFetchPersons){
                break;
            }
            count++;
        }
    }

    @Transactional
    private void saveMovieParticipation(MovieDetails movieDetails, Person person, String department) {
        Participation participation = new Participation();
        participation.setDepartment(department);
        participation.setPerson(person);
        participation.setProgram(movieDao.findByIdTMDB(Long.valueOf(movieDetails.getId())));
        participationDao.save(participation);
    }

    private void fetchTvSeries(){
        int minFetchedTvSeries = 10;
        int fetchedTvSeries = 0;
        int page = 1;
        int numberOfPrograms = 1;

        while(fetchedTvSeries < minFetchedTvSeries) {
            TvSeriesList tvSeriesList = tmdbService.getTrendingTvOfTheWeek(page++);

            for (TvSeriesDetails tvSeriesDetails: tvSeriesList.getTvSeriesDetailsList()) {
                logger.info("Saving ------------------------------ TV Series number: " + numberOfPrograms);
                TvSeriesDetails completeTvSeriesDetails = tmdbService.getTvSeriesById(tvSeriesDetails.getId());
                this.saveTvSeries(completeTvSeriesDetails);
                ProgramCredits programCredits = tmdbService.getTvCreditsById(tvSeriesDetails.getId());
                fetchTvSeriesParticipation(programCredits, tvSeriesDetails);
                numberOfPrograms++;
            }

            fetchedTvSeries += tvSeriesList.getTvSeriesDetailsList().size();
        }
    }

    private void saveTvSeries(TvSeriesDetails tvSeriesDetails) {
        logger.info("Saving Tv Series with TMDB Id " + tvSeriesDetails.getId());

        TvSeries tvSeries = new TvSeries();
        tvSeries.setBackdropPath(tvSeriesDetails.getBackdropPath());
        tvSeries.setIdTMDB(Long.valueOf(tvSeriesDetails.getId()));
        tvSeries.setLanguage(tvSeriesDetails.getLanguage());
        tvSeries.setOverview(tvSeriesDetails.getOverview());
        tvSeries.setReleaseDate(tvSeriesDetails.getReleaseDate());
        tvSeries.setRuntime(tvSeriesDetails.getRuntime().get(0));
        tvSeries.setTitle(tvSeriesDetails.getTitle());
        tvSeries.setSeasons(tvSeriesDetails.getNumberOfSeasons());

        if (!tvSeriesDetails.getCountry().isEmpty()) {
            tvSeries.setCountry(tvSeriesDetails.getCountry().get(0));
        }
        tvSeries = tvSeriesDao.save(tvSeries);

        Set<Genre> genres = getGenresSet(tvSeriesDetails.getGenres());

        tvSeries.setGenres(genres);
        tvSeriesDao.save(tvSeries);
    }

    @Transactional
    private void fetchTvSeriesParticipation(ProgramCredits programCredits, TvSeriesDetails tvSeriesDetails){

        List<CastDetails> castDetailsList = programCredits.getCastDetailsList();
        List<CrewDetails> crewDetailsList = programCredits.getCrewDetailsList();

        int count = 0;
        final int kMaxFetchPersons = 10;

        for(CastDetails castDetails :castDetailsList){
            PersonDetails personDetails = tmdbService.getPersonDetailsById(castDetails.getPersonId());
            Person person = savePerson(personDetails);
            saveTvSeriesParticipation(tvSeriesDetails, person, "Acting");

            if(count == kMaxFetchPersons){
                break;
            }
            count++;
        }
        count = 0;
        for(CrewDetails crewDetails :crewDetailsList){
            PersonDetails personDetails = tmdbService.getPersonDetailsById(crewDetails.getPersonId());
            Person person = savePerson(personDetails);
            saveTvSeriesParticipation(tvSeriesDetails, person, crewDetails.getDepartment());

            if(count == kMaxFetchPersons){
                break;
            }
            count++;
        }
    }

    @Transactional
    private void saveTvSeriesParticipation(TvSeriesDetails tvSeriesDetails, Person person, String department) {
        Participation participation = new Participation();
        participation.setDepartment(department);
        participation.setPerson(person);
        participation.setProgram(tvSeriesDao.findByIdTMDB(Long.valueOf(tvSeriesDetails.getId())));
        participationDao.save(participation);
    }

    @Transactional
    private Person savePerson(PersonDetails personDetails) {

        Person person = personDao.findByIdTMDB(personDetails.getId());

        if(person== null){
            logger.info("Saving person " + personDetails.getName() + " (" + personDetails.getId() + ")");

            person = new Person();

            person.setIdTMDB(personDetails.getId());
            person.setName(personDetails.getName());
            person.setGender(GenderSituation.getGenderSituationByCode(personDetails.getGender()));
            person.setHometown(personDetails.getPlaceOfBirth());
            person.setProfilePath(personDetails.getProfilePath());
            person.setHeight(1.74);
            person.setCountry(personDetails.getPlaceOfBirth());

            person = personDao.save(person);
        }
        return person;
    }

    @Transactional
    private void saveMovie(MovieDetails movieDetails) {
        logger.info("Saving movie with TMDB Id " + movieDetails.getId());

        Movie movie = new Movie();
        movie.setBackdropPath(movieDetails.getBackdropPath());
        movie.setIdTMDB(Long.valueOf(movieDetails.getId()));
        movie.setLanguage(movieDetails.getLanguage());
        movie.setOverview(movieDetails.getOverview());
        movie.setReleaseDate(movieDetails.getReleaseDate());
        movie.setRuntime(movieDetails.getRuntime());
        movie.setTitle(movieDetails.getTitle());

        if (!movieDetails.getCountry().isEmpty()) {
            movie.setCountry(movieDetails.getCountry().get(0).getName());
        }
        movie = movieDao.save(movie);

        Set<Genre> genres = getGenresSet(movieDetails.getGenres());

        movie.setGenres(genres);
        movieDao.save(movie);
    }

    private Set<Genre> getGenresSet(List<ProgramGenre> programGenreList) {
        Set<Genre> genres = new HashSet<Genre>();
        for(ProgramGenre programGenre: programGenreList) {
            Genre genre = genreDao.findByIdTmdb(programGenre.getId());
            if (genre == null) {
                logger.info("Saving program genre " + programGenre.getDescription() + " (" + programGenre.getId() + ")");
                genre = new Genre();
                genre.setIdTmdb(programGenre.getId());
                genre.setDescription(programGenre.getDescription());
                genre = genreDao.save(genre);
            }
            genres.add(genre);
        }
        return genres;
    }
}
