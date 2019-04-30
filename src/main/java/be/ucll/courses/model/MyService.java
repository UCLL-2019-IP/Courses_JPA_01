package be.ucll.courses.model;

import be.ucll.courses.repository.CourseRepository;
import be.ucll.courses.repository.DayRepository;
import be.ucll.courses.repository.WeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class MyService { // be careful, don't call this class "Service"! Conflict with @Service!
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    DayRepository dayRepository;

    @Autowired
    WeekRepository weekRepository;

    public MyService() {
    }


    /*
    ###############################################
    # WEEK
    ###############################################
    */

    public List<Week> getWeeks() {
        return weekRepository.findAll();
    }

    public Optional<Week> getWeekById(int id) {
        return weekRepository.findById(id);
    }

    /*
    ###############################################
    # DAY
    ###############################################
    */

    public List<Day> getDays() {
        return dayRepository.findAll();
    }

    public void addDay(Day day) {
        commitToDatabase(day);

        // check if we need a new week or not
        int weekId = day.getYearAndWeekNumber();
        // if we don't get a week from the database, return a brand new week
        Week week = weekRepository.findById(weekId).orElse(new Week());

        if(week.getId() == 0){ // new week
            week.setId(weekId);

            Map<Integer, Day> weekMap = new HashMap<Integer, Day>();
            weekMap.put(day.getDayOfWeek(), day);
            week.setWeek(weekMap);
        }
        else {
            week.addDay(day); // don't forget to add the day to the week if we have a week already!
        }

        weekRepository.save(week); // always save the week, otherwise nothing shows in the database!
    }

    public void changeDay(LocalDate date, Day changedDay) {
        // need to use the date given in the url so the user can't fiddle with that
        changedDay.setDate(date);

        // need to use the day from the previous version so the user can't fiddle with that
        Day previousVersionOfDay = dayRepository.findById(date).orElseThrow(IllegalArgumentException::new);
        changedDay.setDayName(previousVersionOfDay.getDayName());
        commitToDatabase(changedDay);
    }

    private void commitToDatabase(Day day) {
        // need to do this first because it checks if the course already exists or not
        // and so you don't get doubles in your database
        addCourse(day.getProgramming());
        addCourse(day.getDatabases());
        addCourse(day.getNetworking());
        addCourse(day.getSoftSkills());

        dayRepository.save(day);
    }

    public void deleteDay(LocalDate date) {
        // get the day from the database
        Day day = dayRepository.findById(date).orElseThrow(IllegalArgumentException::new);
        // get the week
        int weekId = day.getYearAndWeekNumber();
        Week week = weekRepository.findById(weekId).orElseThrow(IllegalArgumentException::new);
        week.deleteDay(day);
        // check if the week still contains days, if not, delete it!
        if(week.weekIsEmpty()){
            weekRepository.delete(week);
        }
        else {
            weekRepository.save(week); // need to save week to see the changes in the db
        }
        dayRepository.deleteById(date);
    }

    /*
    ###############################################
    # COURSE
    ###############################################
    */

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseByName(String name) {
        return courseRepository.findByName(name);
    }

    public Course addCourse(Course course) {
        // see if we can fin the course, if we can, we need to set the id
        // otherwise, a new course will be added to the database
        // this is for updating via the REST controller!
        Course courseToAdd = courseRepository.findByName(course.getName());
        if(courseToAdd != null){
            course.setId(courseToAdd.getId());
        }
        return courseRepository.save(course);
    }

    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    public void updateCourse(Course course) {
        courseRepository.save(course);
    }
}
