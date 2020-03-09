package com.simbirsoft.javaexample.controller;

import com.simbirsoft.javaexample.dto.UserDTO;
import com.simbirsoft.javaexample.service.UserService;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController {
    /**
     * Обьект через которого можно совершать операции с БД юзеров
     */
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получение юзеров
     *
     * @return Возвращает список юзеров
     */
    @GetMapping(value = "/user")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    /**
     * Получение юзеров соотвествующий id в формате json
     *
     * @param response Http ответ
     * @param id
     * @return Возвращает список юзеров соотвествующий этому id
     */
    @GetMapping(value = "/user/{id}", produces = {"application/json"})
    public ResponseEntity<List<UserDTO>> getDataJSON(HttpServletResponse response, @PathVariable(name = "id") Integer id) {

        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        return ResponseEntity.ok(userService.getUser(id));
    }

    /**
     * Получение юзеров соотвествующий id в формате xml
     *
     * @param response Http ответ
     * @param id
     * @return Возвращает список юзеров с определнным id
     */
    @GetMapping(value = "/user/{id}", produces = {"application/xhtml+xml"})
    public ResponseEntity<String> getUserXML(HttpServletResponse response, @PathVariable(name = "id") Integer id) {
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/xhtml+xml");
        XStream xStream = new XStream();
        String xml = xStream.toXML(userService.getUser(id));
        return ResponseEntity.ok(xml);
    }

    /**
     * Получение юзеров соотвествующий id в любом формате
     *
     * @param id
     * @return возвращает список юзеров в формате указанный в заголовке Content-Type
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserDTO>> getUser(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(userService.getUser(id));

    }

    /**
     * Добавление юзера в БД
     *
     * @param user обьект который нужно добавить
     * @return Возращает Http статус и сообщение о статусе операции
     */
    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody UserDTO user) {
        boolean result = userService.addUser(user);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Сервер не отвечает!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Пользователь добавлен!");

    }

    /**
     * Удаление юзера
     *
     * @param id ИД который нужно удалить
     * @return Возращает Http статус и сообщение о статусе операции
     */
    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id") Integer id) {
        boolean result = userService.deleteUser(id);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Сервер не отвечает!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Пользователь удален!");
    }
    
    /**
     * Обновление данных юзера
     *
     * @param user обьект пользователя которого нужно обновить
     * @return Возращает Http статус и сообщение о статусе операции
     */
    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity updateUser(@RequestBody UserDTO user) {

        userService.updateUser(user);
        boolean result = userService.updateUser(user);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Сервер не отвечает!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Данные пользователя обновлены!");
    }

}
