package com.simbirsoft.javaexample.service;

import com.simbirsoft.javaexample.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    /**
     * метод добавления предмета в БД универа
     * @param subjectDto ДТО предмета
     * @return true Если сохранение было успешно, false в других случаях
     */
    boolean addSubject(SubjectDto subjectDto);

    /**
     * Метод получения предметов по номеру курса
     * @param course номер курса
     * @return
     */
    List<SubjectDto> getSubject(Integer course);
}
