package com.simbirsoft.javaexample.repository;

import com.simbirsoft.javaexample.data.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PassportRepository extends JpaRepository<Passport,Long> {

    @Query(value ="" +
            "SELECT * " +
            "FROM passport pas " +
            "INNER JOIN person p " +
            "ON p.passport_id = pas.passport_id " +
            "WHERE p.username = :username" ,
            nativeQuery = true)
    Passport findPassportByUsername(@Param("username") String username);
}
