package be.ucll.courses.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(min = 2, max = 15)
    private String name;

    @Size(min = 4, max = 50)
    private String description;

    private CourseType courseType;

    public Course() {
    }

    public Course(@Size(min = 2, max = 15) String name, @Size(min = 4, max = 50) String description, CourseType courseType) {
        this.name = name;
        this.description = description;
        this.courseType = courseType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }
}
