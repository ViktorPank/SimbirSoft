package com.simbirsoft.javaexample.service;

import com.simbirsoft.javaexample.dto.UserDTO;

import java.util.List;

public interface UserService {
    /**
     * метод добавления предмета в БД универа
     * @param userDTO ДТО предмета
     * @return true Если сохранение было успешно, false в других случаях
     */
    boolean addSubject(UserDTO userDTO);

    /**
     * Метод получения юзера по id
     * @param id пользователя которого нужно получить
     * @return список данных юзера по id
     */
    List<UserDTO> getSubject(Integer id);

    /**
     * Метод удаления юзера из листа
     * @param id юзера которого нужно удалить
     * @return true Если удаление было успешно, false в других случаях(не найден пользователь)
     */
    boolean deleteUser(Integer id);

    /**
     * метод обновления данных юзера
     * @param userDTO Обьект пользователя которого нужно обновить
     * @return true если обновление было успешно, false в других случаях (не найден пользовтаель)
     */
    boolean updateUser(UserDTO userDTO);

}
