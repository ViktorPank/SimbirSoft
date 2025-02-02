package com.simbirsoft.javaexample.repository;

import com.simbirsoft.javaexample.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);

    Person findPersonByUsername(String username);
}
