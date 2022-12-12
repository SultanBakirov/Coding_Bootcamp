package com.example.models;

import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "groups")
public class Group {

    @Id
    @SequenceGenerator(name = "group_seq", sequenceName = "group_seq", allocationSize = 1)
    @GeneratedValue(generator = "group_seq")
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "date_of_start")
    private Date dateOfStart;

    private String image;

    private int count;

    public void plusCount(){
        count++;
    }

    public void minusCount(){
        count--;
    }

    @ManyToOne(cascade = {PERSIST, MERGE, REFRESH, DETACH}, fetch = EAGER)
    private Company company;

    @ManyToMany(cascade = {PERSIST, MERGE, REFRESH, DETACH}, mappedBy = "groups")
    private List<Course> courses;
    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        plusCount();
    }

    // Бир группада бир канча студент болуш керек, бир студент бир гана группада боло алат
    @OneToMany(cascade = ALL, fetch = LAZY, mappedBy = "group")
    private List<Student> students;
    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

    public void assignStudent(Student student){
        if (students==null){
            students=new ArrayList<>();
        }
        students.add(student);
    }
}
