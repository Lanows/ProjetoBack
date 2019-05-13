package projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.dto.MovieDto;
import projeto.models.Movie;
import projeto.service.MovieService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/movie")
@CrossOrigin("http://localhost:4200")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<Movie> moviePage = movieService.findAll(pageable);
        return new ResponseEntity<>(moviePage, HttpStatus.OK);
    }

    @GetMapping("/6trending")
    public ResponseEntity<?> get6Trending() {
        ArrayList<Movie> movieList = new ArrayList();
        movieList = movieService.get6Trending();
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/findbyid")
    public ResponseEntity<?> getMovieById(Long id){
        Movie movie = movieService.getMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovieById(@PathVariable Long id, @RequestBody MovieDto movie){
        Movie movieInDatabase = movieService.updateMovieById(id, movie);
        return new ResponseEntity<>(movieInDatabase, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id){
        return movieService.deleteMovie(id);
    }

    @GetMapping("/title")
    public ResponseEntity<?> getMovieByTitle(Pageable pageable, String title){
        Page<Movie> movie = movieService.getMovieByTitle(pageable, title);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/releasedate")
    public ResponseEntity<?> getMovieByReleaseDate(Pageable pageable, String date) throws ParseException {
        Page<Movie> moviePage = movieService.getMovieByReleaseDate(pageable, date);
        return new ResponseEntity<>(moviePage, HttpStatus.OK);
    }

    @GetMapping("/language")
    public ResponseEntity<?> getMovieByLanguage(Pageable pageable, String language){
        Page<Movie> moviePage = movieService.getMovieByLanguage(pageable, language);
        return new ResponseEntity<>(moviePage, HttpStatus.OK);
    }

    @GetMapping("/participation")
    public ResponseEntity<?> findByParticipations(Long id) {
        Movie movie = movieService.findByParticipations(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

}
