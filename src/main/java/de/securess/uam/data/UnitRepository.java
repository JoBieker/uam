package de.securess.uam.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    @Query("select u from Unit u " +
            "where lower(u.name) like lower(concat('%', :searchTerm, '%'))")
    List<Unit> search(String searchTerm);
}
