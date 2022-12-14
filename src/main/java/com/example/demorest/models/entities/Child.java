package com.example.demorest.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "children")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Child extends Person {
    private LocalDate birthDate;
    private boolean toilet;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> allergies = new ArrayList<>();
    @ManyToMany
    @JoinTable(name="tutoring", joinColumns = @JoinColumn(name = "child_id"), inverseJoinColumns = @JoinColumn(name = "tutor_id"))
    private Set<Tutor> tutors = new HashSet<>();
    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations = new HashSet<>();

    public Child(String firstName, String lastName, LocalDate birthDate, boolean toilet) {
        super(firstName, lastName);
        this.birthDate = birthDate;
        this.toilet = toilet;
    }

    public Child(String firstName, String lastName, LocalDate birthDate, boolean toilet, List<String> allergies) {
        super(firstName, lastName);
        this.birthDate = birthDate;
        this.toilet = toilet;
        this.allergies = allergies;
    }

    public Child(String firstName, String lastName, LocalDate birthDate, boolean toilet, List<String> allergies, Set<Tutor> tutors) {
        super(firstName, lastName);
        this.birthDate = birthDate;
        this.toilet = toilet;
        this.allergies = allergies;
        this.tutors = tutors;
    }
}
