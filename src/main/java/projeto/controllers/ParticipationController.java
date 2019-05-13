package projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.models.Participation;
import projeto.service.ParticipationService;

@RestController
@RequestMapping("/participation")
@CrossOrigin("http://localhost:4200")
public class ParticipationController {


    @Autowired
    ParticipationService participationService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<Participation> participationPage = participationService.findAll(pageable);
        return new ResponseEntity<>(participationPage, HttpStatus.OK);
    }

    @GetMapping("/findbyid")
    public ResponseEntity<?> getMovieById(Long id){
        Participation participation = participationService.getParticipationById(id);
        return new ResponseEntity<>(participation, HttpStatus.OK);
    }
}
