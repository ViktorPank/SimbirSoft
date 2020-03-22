package com.simbirsoft.javaexample.repository;

import com.simbirsoft.javaexample.data.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
