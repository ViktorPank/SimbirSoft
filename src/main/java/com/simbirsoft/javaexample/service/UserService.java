package com.simbirsoft.javaexample.service;

import com.simbirsoft.javaexample.dto.UserDTO;

import java.util.List;

public interface UserService {
    /**
     * метод добавления предмета в БД универа
     * @param subjectDto ДТО предмета
     * @return true Если сохранение было успешно, false в других случаях
     */
    boolean addSubject(UserDTO subjectDto);

    /**
     * Метод получения предметов по номеру курса
     * @param course номер курса
     * @return
     */
    List<UserDTO> getSubject(Integer course);
}
