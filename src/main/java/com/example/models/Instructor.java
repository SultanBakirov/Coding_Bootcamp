package com.example.models;

import javax.persistence.*;
import lombok.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "instructors")
public class Instructor {

    @Id
    @SequenceGenerator(name = "instructor_seq", sequenceName = "instructor_seq", allocationSize = 1)
    @GeneratedValue(generator = "instructor_seq")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    private String specialization;

    private int students = 0;

    public void plus(){
        students++;
    }

    public void minus(){
        students--;
    }

    // Бир инструктор бир гана курска сабак ото алат, бир курста бир канча инструктор боло алат
    @ManyToOne(cascade = {MERGE,REFRESH,DETACH},fetch = EAGER)
    private Course course;
}