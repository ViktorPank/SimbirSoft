package com.simbirsoft.javaexample.controller;

import com.simbirsoft.javaexample.dto.SubjectDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SubjectController {

    private static List<SubjectDto> subjectDB = new ArrayList<>( );

    static {
        subjectDB.add(new SubjectDto("Java", 1));
        subjectDB.add(new SubjectDto("Java", 1));
        subjectDB.add(new SubjectDto("Шоттки", 2));
        subjectDB.add(new SubjectDto("Физика", 6));
    }

    @GetMapping("/subjects")
    public List<SubjectDto> getSubjects(@RequestParam(name = "course") Integer course) {
        return getFilterSubjects(course);

    }

    @GetMapping("/subjects/{course}")
    public List<SubjectDto> getSubject(@PathVariable(name = "course") Integer course) {
        return getFilterSubjects(course);

    }

    @PostMapping("/subjects")
    public ResponseEntity addSubjects(@RequestBody SubjectDto subjectDto){

        boolean isSuceed = subjectDB.add(subjectDto);

        if (isSuceed) return ResponseEntity.ok().body("Запрос добавлен");
        else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Запрос не обработан");
    }


    private List<SubjectDto> getFilterSubjects(@RequestParam(name = "course") Integer course) {


        return subjectDB.stream()
                .filter(SubjectDto -> SubjectDto.getCourse() == course)
                .collect(Collectors.toList());

    }
}
