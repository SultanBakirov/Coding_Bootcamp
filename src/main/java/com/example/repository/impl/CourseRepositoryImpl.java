package com.example.repository.impl;

import com.example.models.Company;
import com.example.models.Course;
import com.example.models.Group;
import com.example.repository.CourseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Course> getAllCourses(Long id) {
        return entityManager.createQuery("select c from Course c where c.company.id = :id", Course.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public void addCourse(Long id, Course course) throws IOException {
        Company company = entityManager.find(Company.class, id);
        company.addCourse(course);
        course.setCompany(company);
        entityManager.merge(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public Course updateCourse(Course course, Long id) throws IOException{
        Course course1 = entityManager.find(Course.class, id);
        course1.setCourseName(course.getCourseName());
        course1.setDuration(course.getDuration());
        course1.setDescription(course.getDescription());
        return entityManager.merge(course1);
    }

    @Override
    public void deleteCourseById(Long id) {
        Course course = entityManager.find(Course.class, id);
        for (Group g: course.getGroups()) {
            g.minusCount();
            g.getCourses().remove(course);
        }
        course.setCompany(null);
        entityManager.remove(course);
    }
}
