package de.securess.uam.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r from Role r " +
            "where lower(r.title) like lower(concat('%', :searchTerm, '%'))")
    List<Role> search(@Param("searchTerm") String searchTerm);
}
