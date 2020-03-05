package com.simbirsoft.javaexample.service;

import com.simbirsoft.javaexample.dto.SubjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    private static List<SubjectDto> subjectDB = new ArrayList<>();

    static {
        subjectDB.add(new SubjectDto("Java", 1));
        subjectDB.add(new SubjectDto("Java", 1));
        subjectDB.add(new SubjectDto("Шоттки", 2));
        subjectDB.add(new SubjectDto("Физика", 6));
    }

    @Override
    public boolean addSubject(SubjectDto subjectDto) {
        return subjectDB.add(subjectDto);

    }

    @Override
    public List<SubjectDto> getSubject(Integer course) {

        return subjectDB.stream()
                .filter(SubjectDto -> SubjectDto.getCourse().equals(course))
                .collect(Collectors.toList());
    }
}
