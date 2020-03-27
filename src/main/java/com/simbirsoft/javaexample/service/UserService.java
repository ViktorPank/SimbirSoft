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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Метод добавления юзера в БД
     *
     * @param personDTO DTO юзера которого нужно добавить
     * @return true Если сохранение было успешно, false в других случаях
     */
    public boolean addUser(PersonDTO personDTO) {
        Person personDB = personRepository.findByUsername(personDTO.getUsername());

        if (personDB == null) {
            return false;
        }

        Person person = new Person(personDTO.getUsername(), personDTO.getPassword(), personDTO.getAge());
        person.setRoles(Collections.singleton(new Role(1L, "Role_USER")));
        person.setPassword(bCryptPasswordEncoder.encode(personDTO.getPassword()));
        personRepository.save(person);
        return true;
    }

    /**
     * Метод удаления юзера из листа
     *
     * @param personDTO DTO юзера которого нужно удалить
     * @return true Если удаление было успешно, false в других случаях(не найден пользователь)
     */
    public boolean deleteUser(PersonDTO personDTO) {
        Person personDB = personRepository.findByUsername(personDTO.getUsername());

        if (personDB == null) {
            return false;
        } else personRepository.delete(personDB);

        return true;
    }

    /**
     * обновляет данные юзера
     *
     * @param personDTO DTO юзера которого нужно обновить
     * @return true если обновление было успешно, false в других случаях (не найден пользовтаель)
     */
    public boolean updateUser(PersonDTO personDTO) {
        Person personDB = personRepository.findByUsername(personDTO.getUsername());
        if (personDB == null) {
            return false;
        } else {
            if (!personDB.getAge().equals(personDTO.getAge())) {
                personDB.setAge(personDTO.getAge());
            }
            if (!personDB.getPassword().equals(personDTO.getPassword())) {
                personDB.setPassword(personDTO.getPassword());
            }
            personRepository.save(personDB);
        }
        return true;
    }

    /**
     * Метод возвращает юзеров из БД
     *
     * @return список юзеров
     */
    public List<PersonDTO> getUsers() {
        return personRepository.findAll().stream()
                .map(person -> new PersonDTO(person.getUsername(), person.getPassword(), person.getAge()))
                .collect(Collectors.toList());
    }

    /**
     * Получение списка кредитов юзера
     *
     * @param username имя юзера по которому ищется кредиты
     * @return список кредитов юзера
     */
    public List<CreditDTO> getCredit(String username) {
        List<Credit> creditDB = creditRepository.findCreditByUsername(username);
        return creditDB.stream()
                .map(credit -> new CreditDTO(credit.getAmount(), credit.getCurrency()))
                .collect(Collectors.toList());
    }

    /**
     * Получение паспорта юзера
     *
     * @param username имя юзера по которому ищется паспорт
     * @return паспорт юзера
     */
    public PassportDTO getPassport(String username) {
        Passport passportDB = passportRepository.findPassportByUsername(username);
        return new PassportDTO(passportDB.getSeries(), passportDB.getNumber());
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findPersonByUsername(username);
    }
}
