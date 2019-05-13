package projeto.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.models.Participation;
import projeto.models.Person;

import java.util.Date;

@Repository
public interface IPersonDao extends JpaRepository<Person, Long> {
    Person findByIdTMDB(int idTmdb);
    Person getById(Long id);
    Page<Person> findByNameContainingIgnoreCase(Pageable pageable, String name);
    Person findByParticipations(Participation participation);
}
