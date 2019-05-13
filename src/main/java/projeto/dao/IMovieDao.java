package projeto.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.models.Movie;
import projeto.models.Participation;

import java.util.Date;

@Repository
public interface IMovieDao extends JpaRepository<Movie, Long> {

    Movie findByIdTMDB(Long idTmdb);
    Movie getById(Long id);
    Page<Movie> findByTitleContainingIgnoreCase(Pageable pageable, String title);
    Page<Movie> findByReleaseDateContainingIgnoreCase(Pageable pageable, Date date);
    Page<Movie> findByLanguageContainingIgnoreCase(Pageable pageable, String language);
    Movie findByParticipations(Participation participation);

}
