package com.simbirsoft.javaexample.service;

import com.simbirsoft.javaexample.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
<<<<<<< HEAD
        UserDTO userDTO = userDB
                .stream()
                .filter(UserDto -> UserDto.getId().equals(id))
                .findFirst()
                .orElse(null);
        userDB.remove(userDTO);
=======
        // TODO: Используй subjectDB.stream().filter() чтобы получать нужный или переделай List на Map, где ключом будет id
        for (int i = 0; i < subjectDB.size(); i++) {
            if (subjectDB.get(i).getId().equals(id)) {
                subjectDB.remove(i);
                return true;
            }
        }
>>>>>>> 7ae700f967831ccfd7bae5da2b2abc8c59123ffc

        if (userDTO != null) return true;
        return false;
    }

<<<<<<< HEAD
    /**
     * Получение юзера по id
     *
     * @param id пользователя которого нужно получить
     * @return список юзеров соотвествующий id
     */
=======
    // TODO: Лишний пробел в методе
>>>>>>> 7ae700f967831ccfd7bae5da2b2abc8c59123ffc
    @Override
    public List<UserDTO> getUser(Integer id) {
        return userDB.stream()
                .filter(UserDto -> UserDto.getId().equals(id))
                .collect(Collectors.toList());
    }

<<<<<<< HEAD
    /**
     * Метод обновления объекта с помощью рефлексии
     *
     * @param userDTO Обьект пользователя которого нужно обновить
     * @return возвращает true если обновление прошло успешно, в других случаях вовзращает false
     */
=======
    // TODO: Обновление - это не замена целиком
    // TODO: Ты должен найти в "бд" объект с id == id из userDTO и заменить эквивалентно те поля, которые в userDTO != null
>>>>>>> 7ae700f967831ccfd7bae5da2b2abc8c59123ffc
    @Override
    public boolean updateUser(UserDTO userDTO) {

        /**
         * Получение id и его проверка
         */
        Integer id = userDTO.getId();
        if (id == null) return false;

        /**
         * Получение нужного юзера из базы данных для обработки и его проверка
         */
        UserDTO currentUser = userDB
                .stream()
                .filter(UserDto -> UserDto.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (currentUser == null) return false;

        /**
         * Получение полей из класса с помощью рефлексии
         */
        Field[] fields = UserDTO.class.getDeclaredFields();

        /**
         *Получение из всех полей обьекта данные
         *Изменение в БД данных обьекта если поле не пустое
         */
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;

            try {
                fieldValue = field.get(userDTO);
                if (fieldValue != null) field.set(currentUser, fieldValue);
            } catch (IllegalAccessException e) {
                return false;
            }
        }
        System.out.println();
        return true;
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
