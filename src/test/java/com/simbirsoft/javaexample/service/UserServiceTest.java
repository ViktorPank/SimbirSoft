package com.simbirsoft.javaexample.service;

import com.simbirsoft.javaexample.data.Passport;
import com.simbirsoft.javaexample.data.Person;
import com.simbirsoft.javaexample.dto.PassportDTO;
import com.simbirsoft.javaexample.dto.PersonDTO;
import com.simbirsoft.javaexample.repository.PassportRepository;
import com.simbirsoft.javaexample.repository.PersonRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PassportRepository passportRepository;

    @InjectMocks
    private UserService userService = new UserService();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(personRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Person("Вася","Пупкин",15),
                        new Person("Маша","Расулова",20),
                        new Person("Петя","Тестировщик",25)));
        Mockito.when(personRepository.findPersonByUsername("Вася"))
                .thenReturn(new Person("Вася","1234",15));
        Mockito.when(passportRepository.findPassportByUsername("Вася"))
                .thenReturn(new Passport("12","125465"));

    }

    @Test
    public void getUsersTest() {
        List<PersonDTO> persons = userService.getUsers();
        persons.forEach(personDTO -> Assertions.assertNotEquals(personDTO.getUsername(), null));
        persons.forEach(personDTO -> Assertions.assertNotEquals(personDTO.getPassword(), null));
        persons.forEach(personDTO -> Assertions.assertNotEquals(personDTO.getAge(), null));

    }

    @Test
    public void loadByUsernameTest(){
        String username = "Вася";
        String password = "1234";
        Integer age = 15;
        UserDetails person = userService.loadUserByUsername(username);
        Assertions.assertEquals(person.getUsername(),username);
        Assertions.assertEquals(person.getUsername(),password);
        Assertions.assertEquals(person.getUsername(),age);
    }

    @Test
    public void getPassportTest(){
        String username = "Вася";
        String number = "12";
        String series = "125465";
        PassportDTO passportDTO = userService.getPassport(username);
        Assertions.assertEquals(passportDTO.getNumber(),number);
        Assertions.assertEquals(passportDTO.getSeries(),series);
    }


}
