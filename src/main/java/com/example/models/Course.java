package com.example.models;

import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
    @GeneratedValue(generator = "course_seq")
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    private String duration;

    private String description;

    @ManyToOne(cascade = {PERSIST, MERGE, REFRESH, DETACH}, fetch = EAGER)
    private Company company;

    // Бир курста бир канча группа боло алат, бир группа дагы бир канча курсту ала алат
    @ManyToMany (cascade = {PERSIST, MERGE, REFRESH, DETACH}, fetch = LAZY)
    @JoinTable(
            name = "groups_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups;
    public void addGroup(Group group) {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        groups.add(group);
    }

    @OneToMany(cascade = {MERGE,DETACH,REFRESH,REMOVE},fetch = LAZY, mappedBy = "course")
    private List<Instructor> instructors;
    public void addInstructor(Instructor instructor){
        if (instructors == null){
            instructors = new ArrayList<>();
        }
        instructors.add(instructor);
    }

    // Бир курста бир канча сабак боло алат, бир сабак бир эле курста боло алат
    @OneToMany(cascade = {MERGE, REFRESH, REMOVE, DETACH}, fetch = LAZY, mappedBy = "course")
    private List<Lesson> lessons;
    public void addLesson(Lesson lesson) {
        if (lessons == null) {
            lessons = new ArrayList<>();
        }
        lessons.add(lesson);
    }
}