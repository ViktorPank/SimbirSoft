package com.simbirsoft.javaexample.data;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Person> people;

    public Role() {
    }

    public Role(Long role_id) {
        this.role_id = role_id;
    }

    public Role(Long role_id, String name) {
        this.role_id = role_id;
        this.name = name;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
