package com.simbirsoft.javaexample.service;

import com.simbirsoft.javaexample.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static List<UserDTO> subjectDB = new ArrayList<>();

    static {
        subjectDB.add(new UserDTO("Вася", 10, Arrays.asList("бабушка Фрося", "мама Люся", "папа Игорь"), 1));
        subjectDB.add(new UserDTO("Маша", 12, Arrays.asList("бабушка Леся", "мама Лена", "папа Юра"), 2));
        subjectDB.add(new UserDTO("Петя", 22, Arrays.asList("бабушка Феврония", "мама Юля", "дед Максим"), 3));
        subjectDB.add(new UserDTO("Федор", 43, Arrays.asList("папа Дмитрий ", "мама Алина", "бабушка Диана"), 4));
    }

    @Override
    public boolean addSubject(UserDTO userDTO) {
        return subjectDB.add(userDTO);

    }

    @Override
    public boolean deleteUser(Integer id) {
        // TODO: Используй subjectDB.stream().filter() чтобы получать нужный или переделай List на Map, где ключом будет id
        for (int i = 0; i < subjectDB.size(); i++) {
            if (subjectDB.get(i).getId().equals(id)) {
                subjectDB.remove(i);
                return true;
            }
        }

        return false;
    }

    // TODO: Лишний пробел в методе
    @Override
    public List<UserDTO> getSubject(Integer id) {

        return subjectDB.stream()
                .filter(UserDto -> UserDto.getId().equals(id))
                .collect(Collectors.toList());
    }

    // TODO: Обновление - это не замена целиком
    // TODO: Ты должен найти в "бд" объект с id == id из userDTO и заменить эквивалентно те поля, которые в userDTO != null
    @Override
    public boolean updateUser(UserDTO userDTO) {
        for (int i = 0; i < subjectDB.size(); i++) {
            if (userDTO.getId().equals(subjectDB.get(i).getId())) {
                subjectDB.set(i, userDTO);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<UserDTO> getUsers() {
        return subjectDB;
    }
}
