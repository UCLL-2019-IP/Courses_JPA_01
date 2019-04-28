package be.ucll.courses.controller;

import be.ucll.courses.model.MyService;
import be.ucll.courses.model.Week;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeekController {
    @Autowired
    private MyService weekService;

    @GetMapping("/week")
    public List<Week> getWeeks() {
        return this.weekService.getWeeks();
    }

    @GetMapping("/week/{id}")
    public Week getWeekById(@PathVariable int id) {
        return this.weekService.getWeekById(id).orElseThrow(IllegalArgumentException::new);
    }
}
