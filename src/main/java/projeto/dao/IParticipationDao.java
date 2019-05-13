package projeto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.models.Participation;

@Repository
public interface IParticipationDao extends JpaRepository<Participation, Long> {

    Participation getById(Long id);
}
