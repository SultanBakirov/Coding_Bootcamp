package com.example.service.impl;

import com.example.models.Lesson;
import com.example.repository.LessonRepository;
import com.example.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }


    @Override
    public List<Lesson> getAllLessons(Long id) {
        return lessonRepository.getAllLessons(id);
    }

    @Override
    public void addLesson(Long id, Lesson lesson) {
        lessonRepository.addLesson(id, lesson);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.getLessonById(id);
    }

    @Override
    public void updateLesson(Lesson lesson, Long id) {
        lessonRepository.updateLesson(lesson, id);
    }

    @Override
    public void deleteLessonById(Long id) {
        lessonRepository.deleteLessonById(id);
    }
}
