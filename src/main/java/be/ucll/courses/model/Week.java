package be.ucll.courses.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Week {
    @Id
    private int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany
    @MapKeyColumn(name = "day_number")
    private Map<Integer, Day> week = new HashMap<Integer, Day>();

    public Map<Integer, Day> getWeek() {
        return this.week;
    }

    public void setWeek(Map<Integer, Day> week) {
        this.week = week;
    }

    public void addDay(Day day) {
        this.week.put(day.getDayOfWeek(), day);
    }

    public void deleteDay(Day day) {
        this.week.remove(day.getDayOfWeek());
    }

    public boolean weekIsEmpty(){ return week.isEmpty(); }
}

