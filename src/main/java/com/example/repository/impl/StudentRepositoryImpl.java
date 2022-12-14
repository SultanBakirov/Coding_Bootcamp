package com.example.repository.impl;

import com.example.models.Course;
import com.example.models.Group;
import com.example.models.Instructor;
import com.example.models.Student;
import com.example.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Student> getAllListStudent() {
        return manager.createQuery("select s from Student s", Student.class).getResultList();
    }

    @Override
    public List<Student> getAllStudents(Long id) {
        return manager.createQuery("select s from Student s where s.group.id = :id", Student.class).setParameter("id", id).getResultList();
    }

    @Override
    public void addStudent(Long id, Student student) {
        Group group = manager.find(Group.class, id);
        group.addStudent(student);
        student.setGroup(group);
        manager.merge(student);
        for (Course c:student.getGroup().getCompany().getCourses()) {
            for (Instructor i: c.getInstructors()) {
                i.plus();
            }
        }
    }

    @Override
    public Student getStudentById(Long id) {
        return manager.find(Student.class, id);
    }

    @Override
    public void updateStudent(Student student, Long id) {
        Student student1 = manager.find(Student.class, id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        manager.merge(student1);
    }

    @Override
    public void deleteStudentById(Long id) {
        Student student = manager.find(Student.class, id);
        student.getGroup().getCompany().minusStudent();
        for (Course c:student.getGroup().getCompany().getCourses()) {
            for (Instructor i:c.getInstructors()) {
                i.minus();
            }
        }
        student.setGroup(null);
        manager.remove(student);
    }

    @Override
    public void assignStudent(Long groupId, Long studentId) throws IOException {
        Student student = manager.find(Student.class, studentId);
        Group group = manager.find(Group.class, groupId);
        if (group.getStudents()!=null){
            for (Student g : group.getStudents()) {
                if (g.getId() == studentId) {
                    throw new IOException("This student already exists!");
                }
            }
        }
        for (Course c: student.getGroup().getCompany().getCourses()) {
            for (Instructor i: c.getInstructors()) {
                i.minus();
            }
        }
        for (Course c: group.getCompany().getCourses()) {
            for (Instructor i: c.getInstructors()) {
                i.plus();
            }
        }
        student.getGroup().getStudents().remove(student);
        group.assignStudent(student);
        student.setGroup(group);
        manager.merge(student);
    }
}
