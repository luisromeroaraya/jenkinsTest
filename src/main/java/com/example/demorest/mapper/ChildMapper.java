package com.example.demorest.mapper;

import com.example.demorest.models.dto.ChildDTO;
import com.example.demorest.models.entities.Child;
import com.example.demorest.models.entities.Tutor;
import com.example.demorest.models.forms.ChildCreateForm;
import com.example.demorest.models.forms.ChildUpdateForm;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ChildMapper {
    public ChildDTO toDTO(Child entity) {
        if (entity == null)
           return null;
        return ChildDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDate(entity.getBirthDate())
                .allergies(entity.getAllergies())
                .toilet(entity.isToilet() ? "Yes" : "No")
                .tutorsId(entity.getTutors().stream().map(Tutor::getId).collect(Collectors.toSet()))
                .build();
    }

    public Child toEntity(ChildCreateForm form) {
        if (form == null)
            return null;
        Child child = new Child();
        child.setFirstName(form.getFirstName().trim());
        child.setLastName(form.getLastName().trim());
        child.setBirthDate(form.getBirthDate());
        child.setToilet(form.isToilet());
        child.setAllergies(form.getAllergies().stream().map(String::trim).collect(Collectors.toList()));
        return child;
    }

    public Child toEntity(ChildUpdateForm form) {
        if (form == null)
            return null;
        Child child = new Child();
        child.setFirstName(form.getFirstName().trim());
        child.setLastName(form.getLastName().trim());
        child.setBirthDate(form.getBirthDate());
        child.setToilet(form.isToilet());
        child.setAllergies(form.getAllergies().stream().map(String::trim).collect(Collectors.toList()));
        return child;
    }
}
