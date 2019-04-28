package be.ucll.courses.repository;

import be.ucll.courses.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DayRepository extends JpaRepository<Day, LocalDate> {
}
