package projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.dto.TvSeriesDto;
import projeto.models.TvSeries;
import projeto.service.TvSeriesService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/tv")
@CrossOrigin("http://localhost:4200")
public class TvSeriesController {

    @Autowired
    TvSeriesService tvSeriesService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<TvSeries> tvPage = tvSeriesService.findAll(pageable);
        return new ResponseEntity<>(tvPage, HttpStatus.OK);
    }

    @GetMapping("/6trending")
    public ResponseEntity<?> get6Trending() {
        ArrayList<TvSeries> tvSeriesList = new ArrayList();
        tvSeriesList = tvSeriesService.get6Trending();
        return new ResponseEntity<>(tvSeriesList, HttpStatus.OK);
    }

    @GetMapping("/findbyid")
    public ResponseEntity<?> getTvSeriesById(Long id){
        TvSeries tvSeries = tvSeriesService.getTvSeriesById(id);
        return new ResponseEntity<>(tvSeries, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTvSeriesById(@PathVariable Long id, @RequestBody TvSeriesDto tvSeries){
        TvSeries tvSeriesInDatabase = tvSeriesService.updateTvSeriesById(id, tvSeries);
        return new ResponseEntity<>(tvSeriesInDatabase, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTvSeries(@PathVariable Long id){
        return tvSeriesService.deleteTvSeries(id);
    }

    @GetMapping("/title")
    public ResponseEntity<?> getTvSeriesByTitle(Pageable pageable, String title){
        Page<TvSeries> tvSeries = tvSeriesService.getTvSeriesByTitle(pageable, title);
        return new ResponseEntity<>(tvSeries, HttpStatus.OK);
    }

    @GetMapping("/releasedata")
    public ResponseEntity<?> getTvSeriesByReleaseData(Pageable pageable, String date) throws ParseException {
        Page<TvSeries> tvSeries = tvSeriesService.getTvSeriesByReleaseDate(pageable, date);
        return new ResponseEntity<>(tvSeries, HttpStatus.OK);
    }

    @GetMapping("/language")
    public ResponseEntity<?> getTvSeriesByLanguage(Pageable pageable, String language){
        Page<TvSeries> tvSeries = tvSeriesService.getTvSeriesByLanguage(pageable, language);
        return new ResponseEntity<>(tvSeries, HttpStatus.OK);
    }
}
