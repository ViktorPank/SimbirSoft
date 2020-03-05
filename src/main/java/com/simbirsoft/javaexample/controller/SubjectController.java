package com.simbirsoft.javaexample.controller;

import com.simbirsoft.javaexample.dto.UserDTO;
import com.simbirsoft.javaexample.service.UserService;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class SubjectController {

    private UserService userService;

    @Autowired
    public SubjectController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<UserDTO>> getSubjects(HttpServletResponse response, HttpServletRequest request, @RequestParam(name = "course") Integer course) {
        request.getHeader(HttpHeaders.ACCEPT);
        response.setHeader(HttpHeaders.ACCEPT, "application/json");
        return ResponseEntity.ok(userService.getSubject(course));

    }

    /**
     *
     * @param course
     * @return
     */
    @RequestMapping(value = "/shotki/{course}",produces = {"application/json"},method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getShotkiJSON(@PathVariable(name = "course") Integer course) {

        return ResponseEntity.ok(userService.getSubject(course));
    }

    @RequestMapping(value = "/shotki/{course}",produces = {"application/xhtml+xml"},method = RequestMethod.GET)
    public ResponseEntity<String> getShotkiXML(HttpServletResponse response , @PathVariable(name = "course") Integer course) {

        response.setHeader(HttpHeaders.ACCEPT, "application/xhtml+xml");

        XStream xStream = new XStream();
        String xml = xStream.toXML(userService.getSubject(course));
        return ResponseEntity.ok(xml);
    }

    @GetMapping("/subjects/{course}")
    public ResponseEntity<List<UserDTO>> getSubject(@PathVariable(name = "course") Integer course) {
        return ResponseEntity.ok(userService.getSubject(course));

    }

    @PostMapping("/subjects")
    public ResponseEntity addSubjects(@RequestBody UserDTO subjectDto){
        boolean result = userService.addSubject(subjectDto);
        if(!result){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();

    }



}
