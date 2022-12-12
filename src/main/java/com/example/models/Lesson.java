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
@Table(name = "lessons")
public class Lesson {

    @Id
    @SequenceGenerator(name = "lesson_seq", sequenceName = "lesson_seq", allocationSize = 1)
    @GeneratedValue(generator = "lessont_seq")
    private Long id;

    @Column(name = "lesson_name")
    private String lessonName;

    @ManyToOne(cascade = {PERSIST, MERGE, REFRESH, DETACH}, fetch = EAGER)
    private Course course;

    // Сабака бир канча тапшырма жуктосо болот, тапшырма бир гана сабака тиешелуу болот
    @OneToMany(cascade = ALL, fetch = LAZY, mappedBy = "lesson")
    private List<Task> tasks;
    public void addTask(Task task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
    }
}
