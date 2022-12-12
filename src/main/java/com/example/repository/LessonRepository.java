package com.example.repository;

import com.example.models.Lesson;

import java.util.List;

public interface LessonRepository {

    List<Lesson> getAllLessons(Long id);

    void addLesson(Long id, Lesson lesson);

    Lesson getLessonById(Long id);

    void updateLesson(Lesson lesson, Long id);

    void deleteLessonById(Long id);

}
