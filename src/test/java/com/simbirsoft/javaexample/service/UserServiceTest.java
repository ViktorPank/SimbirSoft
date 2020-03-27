package com.simbirsoft.javaexample.service;

import com.simbirsoft.javaexample.data.Credit;
import com.simbirsoft.javaexample.data.Passport;
import com.simbirsoft.javaexample.data.Person;
import com.simbirsoft.javaexample.dto.CreditDTO;
import com.simbirsoft.javaexample.dto.PassportDTO;
import com.simbirsoft.javaexample.dto.PersonDTO;
import com.simbirsoft.javaexample.repository.CreditRepository;
import com.simbirsoft.javaexample.repository.PassportRepository;
import com.simbirsoft.javaexample.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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

    @Mock
    private CreditRepository creditRepository;

    @InjectMocks
    private UserService userService = new UserService();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(creditRepository.findCreditByUsername("Вася"))
                .thenReturn(Arrays.asList(
                        new Credit(1000L, "RUB"),
                        new Credit(2000L, "USD")));
        Mockito.when(personRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Person("Вася", "Пупкин", 15),
                        new Person("Маша", "Расулова", 20),
                        new Person("Петя", "Тестировщик", 25)));
        Mockito.when(passportRepository.findPassportByUsername("Вася"))
                .thenReturn(new Passport("12", "125465"));
        Mockito.when(personRepository.findPersonByUsername("Вася"))
                .thenReturn(new Person("Вася", "Пупкин", 15));
        Mockito.when(personRepository.findByUsername("Вася"))
                .thenReturn(new Person("Вася", "Пупкин", 15), null);

    }

    @Test
    public void getUsersTest() {

        List<PersonDTO> persons = userService.getUsers();
        persons.forEach(personDTO -> Assertions.assertNotEquals(personDTO.getUsername(), null));
        persons.forEach(personDTO -> Assertions.assertNotEquals(personDTO.getPassword(), null));
        persons.forEach(personDTO -> Assertions.assertNotEquals(personDTO.getAge(), null));
    }

    @Test
    public void loadByUsernameTest() {
        String username = "Вася";
        String password = "Пупкин";
        UserDetails person = userService.loadUserByUsername(username);
        Assertions.assertEquals(person.getUsername(), username);
        Assertions.assertEquals(person.getPassword(), password);
    }

    @Test
    public void getPassportTest() {
        String username = "Вася";
        String number = "125465";
        String series = "12";

        PassportDTO passportDTO = userService.getPassport(username);
        Assertions.assertEquals(passportDTO.getNumber(), number);
        Assertions.assertEquals(passportDTO.getSeries(), series);
    }

    @Test
    public void getCrediTest() {
        String username = "Вася";
        List<CreditDTO> credits = userService.getCredit(username);
        credits.forEach(creditDTO -> Assertions.assertNotEquals(creditDTO.getAmount(), null));
        credits.forEach(creditDTO -> Assertions.assertNotEquals(creditDTO.getCurrency(), null));
    }

    @Test
    public void updateUserTest() {
        boolean result = userService.updateUser(new PersonDTO("Вася", "Пупкин", 15));
        Assertions.assertTrue(result);
        result = userService.updateUser(new PersonDTO("Вася", "Пупкин", 15));
        Assertions.assertTrue(!result);
    }

    @Test
    public void deleteUserTest() {
        boolean result = userService.deleteUser(new PersonDTO("Вася", "Пупкин", 15));
        Assertions.assertTrue(result);
        result = userService.deleteUser(new PersonDTO("Вася", "Пупкин", 15));
        Assertions.assertTrue(!result);
    }

    @Test
    public void addUserTest() {
        boolean result = userService.addUser(new PersonDTO("Вася", "Пупкин", 15));
        Assertions.assertTrue(result);
        result = userService.addUser(new PersonDTO("Вася", "Пупкин", 15));
        Assertions.assertTrue(!result);
    }
}
