package com.simbirsoft.javaexample.service;

import com.simbirsoft.javaexample.data.Person;
import com.simbirsoft.javaexample.dto.CreditDTO;
import com.simbirsoft.javaexample.dto.PassportDTO;
import com.simbirsoft.javaexample.dto.PersonDTO;

import java.util.List;

public interface UserService {
    /**
     * Метод добавления юзера в БД
     *
     * @param personDTO DTO юзера которого нужно добавить
     * @return true Если сохранение было успешно, false в других случаях
     */
    boolean addUser(PersonDTO personDTO);

    /**
     * Метод удаления юзера из листа
     *
     * @param personDTO  DTO юзера которого нужно удалить
     * @return true Если удаление было успешно, false в других случаях(не найден пользователь)
     */
    boolean deleteUser(PersonDTO personDTO);

    /**
     * обновляет данные юзера
     *
     * @param personDTO  DTO юзера которого нужно обновить
     * @return true если обновление было успешно, false в других случаях (не найден пользовтаель)
     */
    boolean updateUser(PersonDTO personDTO);

    /**
     * Метод возвращает юзеров из БД
     *
     * @return список юзеров
     */
    List<PersonDTO> getUsers();

    /**
     * Получение списка кредитов юзера
     * @param username имя юзера по которому ищется кредиты
     * @return список кредитов юзера
     */
    public List<CreditDTO> getCredit(String username);

    /**
     * Получение паспорта юзера
     * @param username имя юзера по которому ищется паспорт
     * @return паспорт юзера
     */
    public PassportDTO getPassport(String username);


}
