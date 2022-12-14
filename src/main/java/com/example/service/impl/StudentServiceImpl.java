package com.example.service.impl;

import com.example.models.Student;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository service;

    @Autowired
    public StudentServiceImpl(StudentRepository service) {
        this.service = service;
    }

    @Override
    public List<Student> getAllListStudent() {
        return service.getAllListStudent();
    }

    @Override
    public List<Student> getAllStudents(Long id) {
        return service.getAllStudents(id);
    }

    @Override
    public void addStudent(Long id, Student student) throws IOException {
        List<Student> students = service.getAllStudents(id);
        for (Student i : students) {
            if (i.getEmail().equals(student.getEmail())){
                throw new IOException("Student with email already exists!");
            }
        }
        validator(student.getPhoneNumber().replace(" ", ""), student.getFirstName().replace(" ", ""), student.getLastName().replace(" ", ""));
        service.addStudent(id,student);
    }

    @Override
    public Student getStudentById(Long id) {
        return service.getStudentById(id);
    }

    @Override
    public void updateStudent(Student student, Long id) throws IOException {
        validator(student.getPhoneNumber().replace(" ", ""), student.getFirstName().replace(" ", ""), student.getLastName().replace(" ", ""));
        service.updateStudent(student,id);
    }

    @Override
    public void deleteStudentById(Long id) {
        service.deleteStudentById(id);
    }

    @Override
    public void assignStudent(Long groupId, Long studentId) throws IOException {
        service.assignStudent(groupId,studentId);
    }

    private void validator(String phone, String firstName, String lastName) throws IOException {
        if (firstName.length()>2 && lastName.length()>2) {
            for (Character i : firstName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("Numbers cannot be inserted in the name of the instructor");
                }
            }

            for (Character i : lastName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("Numbers cannot be inserted into the lastname of the instructor");
                }
            }
        } else {
            throw new IOException("Instructor's first or last name must contain at least 3 letters");
        }

        if (phone.length()==12
                && phone.charAt(0) == '+'
                && phone.charAt(1) == '4'
                && phone.charAt(2) == '8'){
            int counter = 0;

            for (Character i : phone.toCharArray()) {
                if (counter!=0){
                    if (!Character.isDigit(i)) {
                        throw new IOException("Number format is not correct");
                    }
                }
                counter++;
            }
        }else {
            throw new IOException("Number format is not correct");
        }
    }
}
