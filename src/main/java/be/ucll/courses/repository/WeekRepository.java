package be.ucll.courses.repository;

import be.ucll.courses.model.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekRepository extends JpaRepository<Week, Integer> {
}
