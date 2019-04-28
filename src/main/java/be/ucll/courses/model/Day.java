package be.ucll.courses.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;

@Entity
public class Day {
    @Id
    @NotNull
    private LocalDate date;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Course programming;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Course networking;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Course databases;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Course softSkills;

    private String dayName; // MONDAY, TUESDAY, ...
    private int dayOfWeek; // 1 = monday
    private int yearAndWeekNumber; // year 2019 and week 7 => 201907

    public Day() { }

    public Day(LocalDate date, Course programming, Course networking, Course databases, Course softSkills) {
        setDate(date);
        setProgramming(programming);
        setNetworking(networking);
        setDatabases(databases);
        setSoftSkills(softSkills);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        setDayName(date.getDayOfWeek().toString());
        // need this to get the number of the week in the year - 1st week, 2nd week, ...
        TemporalField weekOfWeekBasedYear = WeekFields.ISO.weekOfWeekBasedYear();
        setYearAndWeekNumber(date.getYear(), date.get(weekOfWeekBasedYear));
        // need this to get the number of the day in the week - 1 = monday, 2 = tuesday, ...
        weekOfWeekBasedYear = WeekFields.ISO.dayOfWeek();
        setDayOfWeek(date.get(weekOfWeekBasedYear));
    }

    public int getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(int dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    public String getDayName() { return dayName; }
    public void setDayName(String dayName) {
        this.dayName = dayName.charAt(0) + dayName.substring(1).toLowerCase(); // only first letter uppercase
    }
    public int getYearAndWeekNumber() { return yearAndWeekNumber; }
    public void setYearAndWeekNumber(int year, int weekNumber) {
        String y = String.valueOf(year);
        // make sure we have a zero in front of a 1 digit weekNumber
        String wn = String.format("%02d", weekNumber);
        // this puts the year and the week after each other:
        // eg. 201917 for year 2019 and week 17 or 201907 for year 2019 and week 7
        this.yearAndWeekNumber = Integer.parseInt(y + wn);
    }

    public Course getProgramming() {
        return programming;
    }

    public void setProgramming(Course programming) {
        if (!programming.getCourseType().equals(CourseType.Programming)) {
            throw new IllegalArgumentException("Not a programming course!");
        }
        this.programming = programming;
    }

    public Course getNetworking() {
        return networking;
    }

    public void setNetworking(Course networking) {
        if (!networking.getCourseType().equals(CourseType.Networking)) {
            throw new IllegalArgumentException("Not a networking course!");
        }
        this.networking = networking;
    }

    public Course getDatabases() {
        return databases;
    }

    public void setDatabases(Course databases) {
        if (!databases.getCourseType().equals(CourseType.Databases)) {
            throw new IllegalArgumentException("Not a database course!");
        }
        this.databases = databases;
    }

    public Course getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(Course softSkills) {
        if (!softSkills.getCourseType().equals(CourseType.SoftSkills)) {
            throw new IllegalArgumentException("Not a soft skills course!");
        }
        this.softSkills = softSkills;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Day) {
            Day day = (Day) o;
            if (this.getDate().equals(day.getDate())) return true;
        }
        return false;
    }

}
