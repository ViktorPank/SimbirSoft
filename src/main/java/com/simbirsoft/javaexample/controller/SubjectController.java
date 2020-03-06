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
        return ResponseEntity.ok(userService.getSubject(course));

    }

    //Маппинг который принимает и возвращает json
    @RequestMapping(value = "/user/{id}",produces = {"application/json"},method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getDataJSON(@PathVariable(name = "id") Integer id) {

        return ResponseEntity.ok(userService.getSubject(id));
    }

    //Маппинг который принимает и возвращает xml
    @RequestMapping(value = "/user/{id}",produces = {"application/xhtml+xml"},method = RequestMethod.GET)
    public ResponseEntity<String> getShotkiXML(HttpServletResponse response , @PathVariable(name = "id") Integer id) {

        response.setHeader(HttpHeaders.ACCEPT, "application/xhtml+xml");
        XStream xStream = new XStream();
        String xml = xStream.toXML(userService.getSubject(id));

        return ResponseEntity.ok(xml);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserDTO>> getSubject(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(userService.getSubject(id));

    }

    @PostMapping("/addUser")
    public ResponseEntity addSubjects(@RequestBody UserDTO user){
        boolean result = userService.addSubject(user);
        if(!result){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id") Integer id){
        boolean result = userService.deleteUser(id);
        if(!result){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity updateUser(@RequestBody UserDTO user ){
        boolean result = userService.updateUser(user);
        if(!result){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
