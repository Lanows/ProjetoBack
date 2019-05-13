package projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projeto.dao.IParticipationDao;
import projeto.error.ResourceNotFoundException;
import projeto.models.Participation;

@Service
public class ParticipationService {

    @Autowired
    IParticipationDao participationDao;


    public Page<Participation> findAll(Pageable pageable){
        return participationDao.findAll(pageable);
    }

    public Participation getParticipationById(Long id){

        if (participationDao.existsById(id)){

            return participationDao.getById(id);
        }
        throw new ResourceNotFoundException("There is no Movie with this ID! Sorry About It!");
    }
}
