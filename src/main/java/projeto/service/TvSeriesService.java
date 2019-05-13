package projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import projeto.dao.ITvSeriesDao;
import projeto.dto.TvSeriesDto;
import projeto.error.ResourceNotFoundException;
import projeto.models.TvSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TvSeriesService {

    @Autowired
    ITvSeriesDao tvSeriesDao;

    public Page<TvSeries> findAll(Pageable pageable){
        return tvSeriesDao.findAll(pageable);
    }

    public ArrayList<TvSeries> get6Trending() {
        ArrayList<TvSeries> tvSeriesList = new ArrayList();
        tvSeriesList = (ArrayList<TvSeries>) tvSeriesDao.findAll();
        ArrayList<TvSeries> tvSeriesFinalList = new ArrayList();
        for (int i = 0; i < 6; i++){
            TvSeries list = tvSeriesList.get(i);
            tvSeriesFinalList.add(tvSeriesDao.findByIdTMDB(list.getIdTMDB()));
        }
        return tvSeriesFinalList;
    }

    public TvSeries getTvSeriesById(Long id){
        if (tvSeriesDao.existsById(id)){
            return tvSeriesDao.getById(id);
        }
        throw new ResourceNotFoundException("There is no Tv Series with this ID! Sorry About It!");
    }

    public TvSeries updateTvSeriesById(Long id, TvSeriesDto tvSeries){
        if (tvSeriesDao.existsById(id)){
            TvSeries tvSeriesInDatabase = tvSeriesDao.getById(id);

            if (tvSeries.getTitle() != null) {
                tvSeriesInDatabase.setTitle (tvSeries.getTitle() );
            }

            if (tvSeries.getOverview() != null) {
                tvSeriesInDatabase.setOverview (tvSeries.getOverview() );
            }

            if (tvSeries.getCountry() != null) {
                tvSeriesInDatabase.setCountry (tvSeries.getCountry() );
            }

            if (tvSeries.getLanguage() != null) {
                tvSeriesInDatabase.setLanguage (tvSeries.getLanguage() );
            }

            if (tvSeries.getReleaseDate() != null) {
                tvSeriesInDatabase.setReleaseDate (tvSeries.getReleaseDate() );
            }

            if (tvSeries.getRuntime() != 0) {
                tvSeriesInDatabase.setRuntime (tvSeries.getRuntime() );
            }

            if (tvSeries.getGenres() != null) {
                tvSeriesInDatabase.setGenres (tvSeries.getGenres() );
            }

            if (tvSeries.getParticipations() != null) {
                tvSeriesInDatabase.setParticipations (tvSeries.getParticipations() );
            }

            if (tvSeries.getSeasons() != 0) {
                tvSeriesInDatabase.setSeasons (tvSeries.getSeasons() );
            }

            tvSeriesDao.save(tvSeriesInDatabase);

            return tvSeriesInDatabase;
        }
        throw new ResourceNotFoundException("There is no Tv Series with this ID! Sorry About It!");
    }

    public ResponseEntity<?> deleteTvSeries(@PathVariable Long id){

        try{
            this.tvSeriesDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<>("There is no Tv Series with this ID! Sorry About It!", HttpStatus.NOT_FOUND);
        }
    }

    public Page<TvSeries> getTvSeriesByTitle(Pageable pageable, String title){
        if (tvSeriesDao.findByTitleContainingIgnoreCase(pageable, title) != null){
            Page<TvSeries> tvSeriesPage = tvSeriesDao.findByTitleContainingIgnoreCase(pageable, title);
            return tvSeriesPage;
        }
        throw new ResourceNotFoundException("There is no Tv Series with this Title! Sorry About It!");
    }

    public Page<TvSeries> getTvSeriesByReleaseDate(Pageable pageable, String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = format.parse(date);
        if (tvSeriesDao.findByReleaseDateContainingIgnoreCase(pageable, dateFormat) != null){
            Page<TvSeries> tvSeriesPage = tvSeriesDao.findByReleaseDateContainingIgnoreCase(pageable, dateFormat);
            return tvSeriesPage;
        }
        throw new ResourceNotFoundException("There is no Tv Series with this Date! Sorry About It!");
    }

    public Page<TvSeries> getTvSeriesByLanguage(Pageable pageable, String language){
        if (tvSeriesDao.findByLanguageContainingIgnoreCase(pageable, language) != null){
            Page<TvSeries> tvSeriesPage = tvSeriesDao.findByLanguageContainingIgnoreCase(pageable, language);
            return tvSeriesPage;
        }
        throw new ResourceNotFoundException("There is no Tv Series with this Language! Sorry About It!");
    }
}
