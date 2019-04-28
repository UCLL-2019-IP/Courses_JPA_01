package be.ucll.courses.controller;

import be.ucll.courses.model.Day;
import be.ucll.courses.model.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
public class DayController {
    @Autowired
    private MyService myService;

    @GetMapping("/day")
    public List<Day> getDays() {
        return myService.getDays();
    }

    @PostMapping("/day/add")
    public List<Day> addDay(@RequestBody @Valid Day day) {
        myService.addDay(day);
        return myService.getDays();
    }

    @PatchMapping("/day/change/{date}")
    public List<Day> changeDay(@RequestBody @Valid Day day, @PathVariable String date) {
        // we want to convert the following patterns: yyyy-MM-dd, yyyy/MM/dd, dd-MM-yyyy and dd/MM/yyyy
        // 23-09-2018, 23/09/2018, 2018-09-23 and 2018/09/23 should all convert to the same LocalDate.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd][yyyy/MM/dd][dd-MM-yyyy][dd/MM/yyyy]");
        LocalDate dateFromUrl = LocalDate.parse(date, formatter);

        myService.changeDay(dateFromUrl, day);
        return myService.getDays();
    }

    @DeleteMapping("/day/delete/{date}")
    public void deleteDay(@PathVariable String date) {
        // we want to convert the following patterns: yyyy-MM-dd, yyyy/MM/dd, dd-MM-yyyy and dd/MM/yyyy
        // 23-09-2018, 23/09/2018, 2018-09-23 and 2018/09/23 should all convert to the same LocalDate.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd][yyyy/MM/dd][dd-MM-yyyy][dd/MM/yyyy]");
        LocalDate dateFromUrl = LocalDate.parse(date, formatter);

        myService.deleteDay(dateFromUrl);
    }
}