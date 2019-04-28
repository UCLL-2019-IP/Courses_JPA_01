package be.ucll.courses.controller;

import be.ucll.courses.model.Course;
import be.ucll.courses.model.CourseType;
import be.ucll.courses.model.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class CourseController implements WebMvcConfigurer {
    @Autowired
    private MyService courseService;

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/course")
    public String getAllCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";
    }

    @GetMapping("/course/add")
    public String addCoursePage(Model model) {
        model.addAttribute("values", CourseType.values());
        return "addCourse";
    }

    @PostMapping("/course/add")
    public String addCourse(Model model, @Valid Course course, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            model.addAttribute("values", CourseType.values());
            return "addCourse";
        } else {
            courseService.addCourse(course);
            model.addAttribute("courses", courseService.getAllCourses());
            return "courses";
        }
    }

    @GetMapping(value = "/course/delete", params = {"name"})
    public String deleteCourse(Model model, @RequestParam(value = "name") String name) {
        Course course = courseService.findCourseByName(name);
        model.addAttribute("course", course);
        return "confirmDelete";
    }

    @GetMapping(value = "/course/confirmdelete", params = {"name"})
    public String deleteCourseConfirmed(Model model, @RequestParam(value = "name") String name) {
        Course course = courseService.findCourseByName(name);
        courseService.deleteCourse(course);
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";
    }

    @GetMapping(value = "/course/update", params = {"name"})
    public String courseUpdatePage(Model model, @RequestParam(value = "name") String name) {
        Course course = courseService.findCourseByName(name);
        model.addAttribute("course", course);
        model.addAttribute("values", CourseType.values());
        return "updateCourse";
    }

    @PostMapping("/course/update")
    public String updateCourse(Model model, @Valid Course course, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            model.addAttribute("values", CourseType.values());
            return "updateCourse";
        } else {
            courseService.updateCourse(course);
            model.addAttribute("courses", courseService.getAllCourses());
            return "courses";
        }
    }

    // give the correct error back with this handler, 400 instead of 500
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Requested ID not found!")
    @ExceptionHandler(value = IllegalArgumentException.class)
    public void badIdExecptionHandler() {
        // really nothing to do here
    }
}
