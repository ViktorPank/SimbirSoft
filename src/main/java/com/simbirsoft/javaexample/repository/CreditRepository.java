package com.simbirsoft.javaexample.repository;

import com.simbirsoft.javaexample.data.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {

    @Query(value ="" +
            "SELECT * " +
            "FROM credit c " +
            "INNER JOIN person p " +
            "ON p.person_id = c.person_id" +
            "WHERE p.username = : username",
            nativeQuery = true)
    List<Credit> findCreditByUsername(@Param("username") String username);
}
