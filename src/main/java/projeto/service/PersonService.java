package projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import projeto.dao.IParticipationDao;
import projeto.dao.IPersonDao;
import projeto.dto.PersonDto;
import projeto.error.ResourceNotFoundException;
import projeto.models.Participation;
import projeto.models.Person;

import java.util.ArrayList;

@Service
public class PersonService {


    @Autowired
    IPersonDao personDao;

    @Autowired
    IParticipationDao participationDao;

    @GetMapping
    public Page<Person> findAll(Pageable pageable){
        return personDao.findAll(pageable);
    }

    public ArrayList<Person> get6Trending() {
        ArrayList<Person> personList = new ArrayList();
        personList = (ArrayList<Person>) personDao.findAll();
        ArrayList<Person> personFinalList = new ArrayList();
        for (int i = 0; i < 6; i++){
            Person list = personList.get(i);
            personFinalList.add(personDao.findByIdTMDB(list.getIdTMDB()));
        }
        return personFinalList;
    }
    @GetMapping("/findbyid")
    public Person getPersonById(Long id){
        if (personDao.existsById(id)){
            return personDao.getById(id);
        }
        throw new ResourceNotFoundException("There is no Person with this ID! Sorry About It!");
    }

    @PutMapping("/{id}")
    public Person updatePersonById(Long id, PersonDto person){
        if (personDao.existsById(id)){
            Person personInDatabase = personDao.getById(id);

            if (person.getName() != null) {
                personInDatabase.setName (person.getName() );
            }

            if (person.getCountry() != null) {
                personInDatabase.setCountry (person.getCountry() );
            }

            if (person.getGender() != null) {
                personInDatabase.setGender (person.getGender() );
            }

            if (person.getHeight() != 0) {
                personInDatabase.setHeight (person.getHeight() );
            }

            if (person.getHometown() != null) {
                personInDatabase.setHometown (person.getHometown() );
            }



            if (person.getParticipations() != null) {
                personInDatabase.setParticipations (person.getParticipations() );
            }

            personDao.save(personInDatabase);

            return personInDatabase;
        }
        throw new ResourceNotFoundException("There is no Person with this ID! Sorry About It!");
    }

    @DeleteMapping
    public ResponseEntity<?> deletePerson(@PathVariable Long id){

        try{
            this.personDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(EmptyResultDataAccessException ex){
            return new ResponseEntity<>("There is no Person with this ID! Sorry About It!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/title")
    public Page<Person> getPersonByName(Pageable pageable, String name){
        if (personDao.findByNameContainingIgnoreCase(pageable, name) != null){
            Page<Person> personPage = personDao.findByNameContainingIgnoreCase(pageable, name);
            return personPage;
        }
        throw new ResourceNotFoundException("There is no Person with this ID! Sorry About It!");
    }

    public Person findByParticipations(Long id){
        if (participationDao.existsById(id)){
            Participation participation = participationDao.getById(id);
            Person person = personDao.findByParticipations(participation);
            return person;
        }
        throw new ResourceNotFoundException("There is no Participation with this ID! Sorry About It!");
    }
}
