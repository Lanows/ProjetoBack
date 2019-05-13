package projeto.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.models.TvSeries;

import java.util.Date;

@Repository
public interface ITvSeriesDao extends JpaRepository<TvSeries, Long> {

    TvSeries findByIdTMDB(Long idTmdb);
    TvSeries getById(Long id);
    Page<TvSeries> findByTitleContainingIgnoreCase(Pageable pageable, String title);
    Page<TvSeries> findByReleaseDateContainingIgnoreCase(Pageable pageable, Date date);
    Page<TvSeries> findByLanguageContainingIgnoreCase(Pageable pageable, String language);
}
