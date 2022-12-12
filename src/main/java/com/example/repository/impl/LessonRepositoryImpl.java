package com.example.repository.impl;

import com.example.models.Course;
import com.example.models.Lesson;
import com.example.repository.LessonRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LessonRepositoryImpl implements LessonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Lesson> getAllLessons(Long id) {
        return entityManager.createQuery("select l from Lesson l where l.course.id = :id", Lesson.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public void addLesson(Long id, Lesson lesson) {
        Course course = entityManager.find(Course.class, id);
        course.addLesson(lesson);
        lesson.setCourse(course);
        entityManager.merge(lesson);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return entityManager.find(Lesson.class, id);
    }

    @Override
    public void updateLesson(Lesson lesson, Long id) {
        Lesson lesson1 = entityManager.find(Lesson.class, id);
        lesson1.setLessonName(lesson.getLessonName());
        entityManager.merge(lesson1);
    }

    @Override
    public void deleteLessonById(Long id) {
        entityManager.remove(entityManager.find(Lesson.class, id));
    }
}
