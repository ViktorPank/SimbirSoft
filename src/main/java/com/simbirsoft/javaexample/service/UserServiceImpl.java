package com.simbirsoft.javaexample.service;

import com.simbirsoft.javaexample.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static List<UserDTO> subjectDB = new ArrayList<>();

    static {
        subjectDB.add(new UserDTO("Java", 1, Arrays.asList("бабушка Фрося", "мама Люся", "папа Игорь"), 1));
        subjectDB.add(new UserDTO("Java", 1, Arrays.asList("бабушка Леся", "мама Лена", "папа Юра"), 2));
        subjectDB.add(new UserDTO("Шоттки", 2, Arrays.asList("бабушка Феврония", "мама Юля", "дед Максим"), 3));
        subjectDB.add(new UserDTO("Физика", 6, Arrays.asList("папа Дмитрий ", "мама Алина", "бабушка Диана"), 4));
    }

    @Override
    public boolean addSubject(UserDTO userDTO) {
        return subjectDB.add(userDTO);

    }

    @Override
    public List<UserDTO> getSubject(Integer course) {

        return subjectDB.stream()
                .filter(UserDto -> UserDto.getAge().equals(course))
                .collect(Collectors.toList());
    }
}
