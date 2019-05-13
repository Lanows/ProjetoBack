package projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import projeto.dao.IMovieDao;
import projeto.dao.IParticipationDao;
import projeto.dto.MovieDto;
import projeto.error.ResourceNotFoundException;
import projeto.models.Movie;
import projeto.models.Participation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    IMovieDao movieDao;

    @Autowired
    IParticipationDao participationDao;


    public Page<Movie> findAll(Pageable pageable){
        return movieDao.findAll(pageable);
    }

    public ArrayList<Movie> get6Trending() {
        ArrayList<Movie> movieList = new ArrayList();
        movieList = (ArrayList<Movie>) movieDao.findAll();
        ArrayList<Movie> movieFinalList = new ArrayList();
        for (int i = 0; i < 6; i++){
            Movie list = movieList.get(i);
            movieFinalList.add(movieDao.findByIdTMDB(list.getIdTMDB()));
        }
        return movieFinalList;
    }


    public Movie getMovieById(Long id){
        if (movieDao.existsById(id)){
            return movieDao.getById(id);
        }
        throw new ResourceNotFoundException("There is no Movie with this ID! Sorry About It!");
    }


    public Movie updateMovieById(Long id, MovieDto movie){
        if (movieDao.existsById(id)){
            Movie movieInDatabase = movieDao.getById(id);

            if (movie.getTitle() != null) {
                movieInDatabase.setTitle (movie.getTitle() );
            }

            if (movie.getOverview() != null) {
                movieInDatabase.setOverview (movie.getOverview() );
            }

            if (movie.getCountry() != null) {
                movieInDatabase.setCountry (movie.getCountry() );
            }

            if (movie.getLanguage() != null) {
                movieInDatabase.setLanguage (movie.getLanguage() );
            }

            if (movie.getReleaseDate() != null) {
                movieInDatabase.setReleaseDate (movie.getReleaseDate() );
            }

            if (movie.getRuntime() != 0) {
                movieInDatabase.setRuntime (movie.getRuntime() );
            }

            if (movie.getGenres() != null) {
            movieInDatabase.setGenres (movie.getGenres() );
            }

            if (movie.getParticipations() != null) {
            movieInDatabase.setParticipations (movie.getParticipations() );
            }

            movieDao.save(movieInDatabase);

            return movieInDatabase;
        }
        throw new ResourceNotFoundException("There is no Movie with this ID! Sorry About It!");
    }


    public ResponseEntity<?> deleteMovie(Long id){

        try{
            this.movieDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<>("There is no Movie with this ID! Sorry About It!", HttpStatus.NOT_FOUND);
        }
    }


    public Page<Movie> getMovieByTitle(Pageable pageable, String title){
        if (movieDao.findByTitleContainingIgnoreCase(pageable, title) != null){
            Page<Movie> moviePage = movieDao.findByTitleContainingIgnoreCase(pageable, title);
            return moviePage;
        }
        throw new ResourceNotFoundException("There is no Movie with this ID! Sorry About It!");
    }


    public Page<Movie> getMovieByReleaseDate(Pageable pageable, String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = format.parse(date);
        if (movieDao.findByReleaseDateContainingIgnoreCase(pageable, dateFormat) != null){
            Page<Movie> moviePage = movieDao.findByReleaseDateContainingIgnoreCase(pageable, dateFormat);
            return moviePage;
        }
        throw new ResourceNotFoundException("There is no Movie with this ID! Sorry About It!");
    }


    public Page<Movie> getMovieByLanguage(Pageable pageable, String language){
        if (movieDao.findByLanguageContainingIgnoreCase(pageable, language) != null){
            Page<Movie> moviePage = movieDao.findByLanguageContainingIgnoreCase(pageable, language);
            return moviePage;
        }
        throw new ResourceNotFoundException("There is no Movie with this ID! Sorry About It!");
    }

    public Movie findByParticipations(Long id){
        if (participationDao.existsById(id)){
            Participation participation = participationDao.getById(id);
            Movie movie = movieDao.findByParticipations(participation);
            return movie;
        }
        throw new ResourceNotFoundException("There is no Participation with this ID! Sorry About It!");
    }
}
