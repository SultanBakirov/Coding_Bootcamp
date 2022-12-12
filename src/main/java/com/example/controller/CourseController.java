package com.example.controller;

import com.example.models.Course;
import com.example.models.Group;
import com.example.models.Instructor;
import com.example.models.Student;
import com.example.service.CourseService;
import com.example.service.GroupService;
import com.example.service.InstructorService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class CourseController {

    private final CourseService courseService;
    private final GroupService groupService;
    private final InstructorService instructorService;

    private final StudentService studentService;


    @Autowired
    public CourseController(CourseService courseService, GroupService groupService, InstructorService instructorService, StudentService studentService) {
        this.courseService = courseService;
        this.groupService = groupService;
        this.instructorService = instructorService;
        this.studentService = studentService;
    }

    @GetMapping("/courses/{id}")
    public String listCourses(@PathVariable Long id, Model model,
                              @ModelAttribute("group") Group group,
                              @ModelAttribute("instructor") Instructor instructor) {
        model.addAttribute("courses", courseService.getAllCourses(id));
        model.addAttribute("groups", groupService.getAllGroups(id));
        model.addAttribute("instructors", instructorService.getAllInstructors());
        model.addAttribute("students", studentService.getAllListStudent());
        model.addAttribute("companyId", id);
        return "/course/courses";
    }

    @GetMapping("/courses/{id}/new")
    public String createCourseForm(@PathVariable Long id, Model model) {
        model.addAttribute("companyId", id);
        model.addAttribute("course", new Course());
        return "/course/create_course";
    }

    @PostMapping("/{id}/courses")
    public String saveCourse(@PathVariable Long id, @ModelAttribute("course") Course course) {
        courseService.addCourse(id, course);
        return "redirect:/courses/" + id;
    }

    @GetMapping("/update/edit/{id}")
    public String editCourseForm(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("companyId", course.getCompany().getId());
        return "/course/edit_course";
    }

    @PostMapping("/course/{id}/{companyId}")
    public String updateCourse(@PathVariable("companyId") Long companyId,
                               @PathVariable("id") Long id,
                               @ModelAttribute("course") Course course) {
        courseService.updateCourse(course, id);
        return "redirect:/courses/" + companyId;
    }

    @GetMapping("/{companyId}/{id}/delete")
    public String deleteCourse(@PathVariable("id") Long id, @PathVariable("companyId") Long companyId) {
        courseService.deleteCourseById(id);
        return "redirect:/courses/" + companyId;
    }

    @PostMapping("{companyId}/{courseId}/assignGroup")
    private String assignGroup(@PathVariable("companyId") Long comId,
                               @PathVariable("courseId") Long courseId,
                               @ModelAttribute("group") Group group)
            throws IOException {
        System.out.println(group);
        groupService.assignGroup(courseId, group.getId());
        return "redirect:/groups/" + comId+"/"+courseId;
    }

    @PostMapping("/{courseId}/assignInstructor")
    private String assignInstructor(@PathVariable("courseId") Long courseId,
                                    @ModelAttribute("instructor") Instructor instructor)
            throws IOException {
        System.out.println(instructor);
        instructorService.assignInstructor(courseId, instructor.getId());
        return "redirect:/instructors/"+courseId;
    }

    @PostMapping("/{groupId}/assignStudent")
    private String assignStudent(@PathVariable("groupId") Long groupId,
                                 @ModelAttribute("student") Student student)
            throws IOException {
        System.out.println(student);
        studentService.assignStudent(groupId, student.getId());
        return "redirect:/students/"+groupId;
    }
}
