package com.example.demorest.controllers;

import com.example.demorest.models.dto.TutorDTO;
import com.example.demorest.models.forms.TutorAddForm;
import com.example.demorest.models.forms.TutorUpdateForm;
import com.example.demorest.services.TutorService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/tutors")
@Secured({"ROLE_USER"})
public class TutorController {
    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @GetMapping("/{id:[0-9]+}")
    public TutorDTO getOne(@PathVariable Long id) {
        return tutorService.getOne(id);
    }

    @GetMapping("/all")
    public List<TutorDTO> getAll() {
        return tutorService.getAll();
    }

    @PostMapping ("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TutorDTO save(@Valid @RequestBody TutorAddForm tutorAddForm) {
        return tutorService.create(tutorAddForm);
    }

    @PutMapping("/update/{id}") // PUT updates every attribute, PATCH updates ony the specified attributes
    public TutorDTO update(@Valid @PathVariable Long id, @RequestBody TutorUpdateForm tutorUpdateForm) {
        return tutorService.update(id, tutorUpdateForm);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        tutorService.delete(id);
    }

    @GetMapping("/city/{city}")
    public List<TutorDTO> getAllFromCity(@PathVariable String city) {
        return tutorService.getAllFromCity(city);
    }
}
