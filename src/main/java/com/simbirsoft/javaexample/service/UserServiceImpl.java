package com.simbirsoft.javaexample.service;

import com.simbirsoft.javaexample.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static List<UserDTO> subjectDB = new ArrayList<>();

    static {
        subjectDB.add(new UserDTO("Java", 1));
        subjectDB.add(new UserDTO("Java", 1));
        subjectDB.add(new UserDTO("Шоттки", 2));
        subjectDB.add(new UserDTO("Физика", 6));
    }

    @Override
    public boolean addSubject(UserDTO userDTO) {
        return subjectDB.add(userDTO);

    }

    @Override
    public List<UserDTO> getSubject(Integer course) {

        return subjectDB.stream()
                .filter(UserDto -> UserDto.getCourse().equals(course))
                .collect(Collectors.toList());
    }
}
