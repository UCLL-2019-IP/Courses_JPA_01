package be.ucll.courses.model;

import be.ucll.courses.repository.CourseRepository;
import be.ucll.courses.repository.DayRepository;
import be.ucll.courses.repository.WeekRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

// Bridge between Spring Boot test features and JUnit
@RunWith(SpringRunner.class)
public class MyServiceTest {
    @TestConfiguration
    static class FeedbackServiceTestContextConfiguration {

        // Creates an instance of service in order to be able to autowire it
        @Bean
        public MyService service() {
            return new MyService();
        }
    }

    @Autowired
    private MyService service;

    @MockBean
    private CourseRepository courseRepository;
    @MockBean
    private DayRepository dayRepository;
    @MockBean
    private WeekRepository weekRepository;

    private Course c1, c2;

    @Before
    public void setUp() throws Exception {
        c1 = new Course("IP", "Internet programming", CourseType.Programming);
        c2 = new Course("CR", "Conflict resolution", CourseType.SoftSkills);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getWeeks() {
    }

    @Test
    public void getWeekById() {
    }

    @Test
    public void getDays() {
    }

    @Test
    public void addDay() {
    }

    @Test
    public void changeDay() {
    }

    @Test
    public void deleteDay() {
    }

    @Test
    public void getAllCourses() {
    }

    @Test
    public void findCourseByName() {
    }

    @Test
    public void addCourse() {
        Mockito.when(courseRepository.save(c1)).thenReturn(c1);
        assertEquals(c1, service.addCourse(c1));
    }

    @Test
    public void deleteCourse() {
        service.deleteCourse(c1);
        Mockito.verify(courseRepository, Mockito.times(1)).delete(c1);
    }

    @Test
    public void updateCourse() {
    }
}