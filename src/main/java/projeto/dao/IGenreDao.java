package projeto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.models.Genre;

@Repository
public interface IGenreDao extends JpaRepository<Genre, Long> {
    Genre findByIdTmdb(int idTmdb);
}