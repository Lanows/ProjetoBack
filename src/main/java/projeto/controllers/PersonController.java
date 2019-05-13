package projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.dto.PersonDto;
import projeto.models.Person;
import projeto.service.PersonService;

import java.util.ArrayList;

@RestController
@RequestMapping("/person")
@CrossOrigin("http://localhost:4200")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<Person> personPage = personService.findAll(pageable);
        return new ResponseEntity<>(personPage, HttpStatus.OK);
    }

    @GetMapping("/6trending")
    public ResponseEntity<?> get6Trending() {
        ArrayList<Person> personList = new ArrayList();
        personList = personService.get6Trending();
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping("/findbyid")
    public ResponseEntity<?> getPersonById(Long id){
        Person person = personService.getPersonById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersonById(@PathVariable Long id, @RequestBody PersonDto person){
        Person personInDatabase = personService.updatePersonById(id, person);
        return new ResponseEntity<>(personInDatabase, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id){
        return personService.deletePerson(id);
    }

    @GetMapping("/name")
    public ResponseEntity<?> getPersonByName(Pageable pageable, String name){
        Page<Person> person = personService.getPersonByName(pageable, name);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/participation")
    public ResponseEntity<?> findByParticipations(Long id) {
        Person person = personService.findByParticipations(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
