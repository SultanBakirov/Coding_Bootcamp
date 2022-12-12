package com.example.models;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
    @GeneratedValue(generator = "task_seq")
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_text")
    private String taskText;

    private LocalDate deadline;

    @ManyToOne(cascade = {PERSIST,MERGE,REFRESH,DETACH}, fetch = EAGER)
    private Lesson lesson;
}
