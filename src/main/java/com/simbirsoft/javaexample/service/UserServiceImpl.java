package com.simbirsoft.javaexample.service;

import com.simbirsoft.javaexample.data.Credit;
import com.simbirsoft.javaexample.data.Passport;
import com.simbirsoft.javaexample.data.Person;
import com.simbirsoft.javaexample.data.Role;
import com.simbirsoft.javaexample.dto.CreditDTO;
import com.simbirsoft.javaexample.dto.PassportDTO;
import com.simbirsoft.javaexample.dto.PersonDTO;
import com.simbirsoft.javaexample.repository.CreditRepository;
import com.simbirsoft.javaexample.repository.PassportRepository;
import com.simbirsoft.javaexample.repository.PersonRepository;
import com.simbirsoft.javaexample.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private PersonRepository personRepository;
    private CreditRepository creditRepository;
    private PassportRepository passportRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(PersonRepository personRepository, CreditRepository creditRepository, PassportRepository passportRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personRepository = personRepository;
        this.creditRepository = creditRepository;
        this.passportRepository = passportRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean addUser(PersonDTO personDTO) {
        Person personDB = personRepository.findByUsername(personDTO.getUsername());

        if (personDB != null) {
            return false;
        }

        Person person = new Person(personDTO.getUsername(), personDTO.getPassword(), personDTO.getAge());
        person.setRoles(Collections.singleton(new Role(1L, "Role_USER")));
        person.setPassword(bCryptPasswordEncoder.encode(personDTO.getPassword()));
        personRepository.save(person);
        return true;

    }

    @Override
    public boolean deleteUser(PersonDTO personDTO) {
        Person personDB = personRepository.findByUsername(personDTO.getUsername());

        if (personDB != null) {
            return false;
        } else personRepository.delete(personDB);

        return true;
    }

    @Override
    public boolean updateUser(PersonDTO personDTO) {
        Person personDB = personRepository.findByUsername(personDTO.getUsername());

        if (personDB != null) {
            return false;
        } else {
            if (personDB.getAge() != personDTO.getAge()) personDB.setAge(personDTO.getAge());
            if (personDB.getPassword() != personDTO.getPassword()) personDB.setPassword(personDTO.getPassword());
            personRepository.save(personDB);
        }

        return true;
    }

    @Override
    public List<PersonDTO> getUsers() {
        return personRepository.findAll().stream()
                .map(person -> new PersonDTO(person.getUsername(), person.getPassword(), person.getAge()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCredit(String username) {
        List<Credit> creditDB = creditRepository.findCreditByUsername(username);
        return creditDB.stream()
                .map(credit -> new CreditDTO(credit.getAmount(), credit.getCurrency()))
                .collect(Collectors.toList());
    }

    @Override
    public PassportDTO getPassport(String username) {
        Passport passportDB = passportRepository.findPassportByUsername(username);
        return new PassportDTO(passportDB.getSeries(), passportDB.getNumber());
    }
}
