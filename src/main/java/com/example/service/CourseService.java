package com.example.service;

import com.example.models.Course;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses(Long id);

    void addCourse(Long id, Course course);

    Course getCourseById(Long id);

    Course updateCourse(Course course, Long id);

    void deleteCourseById(Long id);

}
