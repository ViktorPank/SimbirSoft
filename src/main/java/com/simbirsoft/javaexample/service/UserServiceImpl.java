package com.simbirsoft.javaexample.service;

import com.simbirsoft.javaexample.dto.UserDTO;
import com.simbirsoft.javaexample.util.UserReflection;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {
    /**
     * Создание БД
     */
    private static List<UserDTO> userDB = new ArrayList<>();

    static {
        userDB.add(new UserDTO("Вася", 10, Arrays.asList("бабушка Фрося", "мама Люся", "папа Игорь"), 1));
        userDB.add(new UserDTO("Маша", 12, Arrays.asList("бабушка Леся", "мама Лена", "папа Юра"), 2));
        userDB.add(new UserDTO("Петя", 22, Arrays.asList("бабушка Феврония", "мама Юля", "дед Максим"), 3));
        userDB.add(new UserDTO("Федор", 43, Arrays.asList("папа Дмитрий ", "мама Алина", "бабушка Диана"), 4));
    }

    /**
     * Добавление юзера в БД
     *
     * @param userDTO обьект юзера которого нужно добавить
     * @return true если добавил, иначе false
     */
    @Override
    public boolean addUser(UserDTO userDTO) {
        return userDB.add(userDTO);

    }

    /**
     * Удаление юзера из БД
     *
     * @param id юзера которого нужно удалить
     * @return true если добавил, иначе false
     */
    @Override
    public boolean deleteUser(Integer id) {
        UserDTO userDTO = userDB.stream()
                .filter(UserDto -> UserDto.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (userDTO != null)userDB.remove(userDTO);
        return userDTO != null;
    }

    /**
     * Получение юзера по id
     *
     * @param id пользователя которого нужно получить
     * @return список юзеров соотвествующий id
     */
    @Override
    // TODO: Теперь можно дополнить метод и принимать на вход не один id, а DTOшку пользователя и осущевлять фильтрацию по всем полям
    public List<UserDTO> getUser(Integer id) {
        return userDB.stream()
                .filter(UserDto -> UserDto.getId().equals(id))
                .collect(Collectors.toList());
    }

    /**
     * Метод обновления объекта с помощью рефлексии
     *
     * @param userDTO Обьект пользователя которого нужно обновить
     * @return возвращает true если обновление прошло успешно, в других случаях вовзращает false
     */

    @Override
    public boolean updateUser(UserDTO userDTO) {


        Integer id = userDTO.getId();
        if (id != null) {
            UserDTO currentUser = userDB
                    .stream()
                    .filter(UserDto -> UserDto.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            return currentUser != null ? new UserReflection().updateUserReflection(userDTO,currentUser) : false;
        }
        else return false;




    }

    /**
     * Получение юзеров
     *
     * @return список всех юзеров
     */
    @Override
    public List<UserDTO> getUsers() {
        return userDB;
    }
}
