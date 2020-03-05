package com.simbirsoft.javaexample.controller;

import com.simbirsoft.javaexample.dto.SubjectDto;
import com.simbirsoft.javaexample.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// TODO: Пользуйся форматированием и не оставляй пробелы в конце тела метода
@RestController
public class SubjectController {

    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectDto>> getSubjects(HttpServletRequest request, @RequestParam(name = "course") Integer course) {
        // TODO: Ради чего вызываем тут этот метод?
        request.getHeader(HttpHeaders.ACCEPT);
        return ResponseEntity.ok(subjectService.getSubject(course));

    }

    @GetMapping("/subjects/{course}")
    public ResponseEntity<List<SubjectDto>> getSubject(@PathVariable(name = "course") Integer course) {
        return ResponseEntity.ok(subjectService.getSubject(course));

    }

    @PostMapping("/subjects")
    public ResponseEntity addSubjects(@RequestBody SubjectDto subjectDto){
        boolean result = subjectService.addSubject(subjectDto);
        if(!result){
            // TODO: Хорошо бы еще добавить DTO с одним полем - текстом ошибки, которое будет возвращаться при ошибках
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    // TODO: А где методы обновления и удаления?



}
