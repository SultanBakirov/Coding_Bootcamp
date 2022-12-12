package com.example.repository;

import com.example.models.Course;

import java.util.List;

public interface CourseRepository {

    List<Course> getAllCourses(Long id);

    void addCourse(Long id, Course course);

    Course getCourseById(Long id);

    Course updateCourse(Course course, Long id);

    void deleteCourseById(Long id);

}
