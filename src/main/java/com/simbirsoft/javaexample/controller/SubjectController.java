package com.simbirsoft.javaexample.controller;

import com.simbirsoft.javaexample.dto.SubjectDto;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SubjectController {

    private List<SubjectDto> subjectDB = Arrays.asList(
            new SubjectDto("Java", 1),
            new SubjectDto("Java", 1),
            new SubjectDto("Шоттки", 2),
            new SubjectDto("Физика", 6)
    );

    @GetMapping("/subjects")
    public List<SubjectDto> getSubjects(@RequestParam(name = "course") Integer course) {
        return getFilterSubjects(course);

    }

    @GetMapping("/subjects/{course}")
    public List<SubjectDto> getSubject(@PathVariable(name = "course") Integer course) {
        return getFilterSubjects(course);

    }

    @PostMapping("/subjects")
    public void addSubjects(@RequestBody SubjectDto subjectDto){
        subjectDB.add(subjectDto);

    }


    private List<SubjectDto> getFilterSubjects(@RequestParam(name = "course") Integer course) {


        return subjectDB.stream()
                .filter(SubjectDto -> SubjectDto.getCourse() == course)
                .collect(Collectors.toList());

    }
}
