package be.ucll.courses;

import be.ucll.courses.model.Course;
import be.ucll.courses.model.CourseType;
import be.ucll.courses.model.Day;
import be.ucll.courses.model.Week;
import be.ucll.courses.repository.CourseRepository;
import be.ucll.courses.repository.DayRepository;
import be.ucll.courses.repository.WeekRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Order(1)
    public CommandLineRunner runnerCourses(CourseRepository courseRepository) {
        return CoursesArgs -> {
            courseRepository.save(new Course("IP", "Internet programming", CourseType.Programming));
            courseRepository.save(new Course("DP", "Distributed programming", CourseType.Programming));
            courseRepository.save(new Course("NW1", "Networking 1", CourseType.Networking));
            courseRepository.save(new Course("IoT1", "Internet of Things 1", CourseType.Networking));
            courseRepository.save(new Course("DB1", "Databases 1", CourseType.Databases));
            courseRepository.save(new Course("DB2", "Databases 2", CourseType.Databases));
            courseRepository.save(new Course("C1", "Communication 1", CourseType.SoftSkills));
            courseRepository.save(new Course("CR", "Conflict resolution", CourseType.SoftSkills));
        };
    }

    @Bean
    @Order(2)
    public CommandLineRunner runnerDays(DayRepository dayRepository, CourseRepository courseRepository) {
        return DayArgs -> {
            dayRepository.save(new Day(LocalDate.of(2019,4, 22),
                    courseRepository.findByName("IP"),
                    courseRepository.findByName("NW1"),
                    courseRepository.findByName("DB1"),
                    courseRepository.findByName("C1")));

            dayRepository.save(new Day(LocalDate.of(2019,4, 23),
                    courseRepository.findByName("DP"),
                    courseRepository.findByName("IoT1"),
                    courseRepository.findByName("DB2"),
                    courseRepository.findByName("CR")));

            dayRepository.save(new Day(LocalDate.of(2019,4, 24),
                    courseRepository.findByName("IP"),
                    courseRepository.findByName("NW1"),
                    courseRepository.findByName("DB1"),
                    courseRepository.findByName("C1")));

            dayRepository.save(new Day(LocalDate.of(2019,4, 25),
                    courseRepository.findByName("DP"),
                    courseRepository.findByName("IoT1"),
                    courseRepository.findByName("DB2"),
                    courseRepository.findByName("CR")));

            dayRepository.save(new Day(LocalDate.of(2019,4, 26),
                    courseRepository.findByName("IP"),
                    courseRepository.findByName("NW1"),
                    courseRepository.findByName("DB1"),
                    courseRepository.findByName("C1")));

            // new week
            dayRepository.save(new Day(LocalDate.of(2019,4, 29),
                    courseRepository.findByName("DP"),
                    courseRepository.findByName("IoT1"),
                    courseRepository.findByName("DB2"),
                    courseRepository.findByName("CR")));

            dayRepository.save(new Day(LocalDate.of(2019,4, 30),
                    courseRepository.findByName("IP"),
                    courseRepository.findByName("NW1"),
                    courseRepository.findByName("DB1"),
                    courseRepository.findByName("C1")));

            dayRepository.save(new Day(LocalDate.of(2019,5, 1),
                    courseRepository.findByName("DP"),
                    courseRepository.findByName("IoT1"),
                    courseRepository.findByName("DB2"),
                    courseRepository.findByName("CR")));

            dayRepository.save(new Day(LocalDate.of(2019,5, 2),
                    courseRepository.findByName("IP"),
                    courseRepository.findByName("NW1"),
                    courseRepository.findByName("DB1"),
                    courseRepository.findByName("C1")));

            dayRepository.save(new Day(LocalDate.of(2019,5, 3),
                    courseRepository.findByName("DP"),
                    courseRepository.findByName("IoT1"),
                    courseRepository.findByName("DB2"),
                    courseRepository.findByName("CR")));
        };
    }

    @Bean
    @Order(3)
    public CommandLineRunner runnerWeekss(WeekRepository weekRepository, DayRepository dayRepository) {
        return WeekArgs -> {
            Week week1 = new Week();
            Day day = dayRepository.findById(LocalDate.of(2019,4, 22)).orElseThrow(IllegalArgumentException::new);
            int yearAndWeekNumber = day.getYearAndWeekNumber();
            week1.setId(yearAndWeekNumber);

            week1.addDay(day);
            week1.addDay(dayRepository.findById(LocalDate.of(2019,4, 23)).orElseThrow(IllegalArgumentException::new));
            week1.addDay(dayRepository.findById(LocalDate.of(2019,4, 24)).orElseThrow(IllegalArgumentException::new));
            week1.addDay(dayRepository.findById(LocalDate.of(2019,4, 25)).orElseThrow(IllegalArgumentException::new));
            week1.addDay(dayRepository.findById(LocalDate.of(2019,4, 26)).orElseThrow(IllegalArgumentException::new));

            weekRepository.save(week1);

            Week week2 = new Week();
            day = dayRepository.findById(LocalDate.of(2019,4, 29)).orElseThrow(IllegalArgumentException::new);
            yearAndWeekNumber = day.getYearAndWeekNumber();
            week2.setId(yearAndWeekNumber);

            week2.addDay(day);
            week2.addDay(dayRepository.findById(LocalDate.of(2019,4, 30)).orElseThrow(IllegalArgumentException::new));
            week2.addDay(dayRepository.findById(LocalDate.of(2019,5, 1)).orElseThrow(IllegalArgumentException::new));
            week2.addDay(dayRepository.findById(LocalDate.of(2019,5, 2)).orElseThrow(IllegalArgumentException::new));
            week2.addDay(dayRepository.findById(LocalDate.of(2019,5, 3)).orElseThrow(IllegalArgumentException::new));

            weekRepository.save(week2);
        };
    }
}

