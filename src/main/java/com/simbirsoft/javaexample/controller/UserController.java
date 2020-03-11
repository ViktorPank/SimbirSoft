package com.simbirsoft.javaexample.controller;

import com.simbirsoft.javaexample.dto.MessageDTO;
import com.simbirsoft.javaexample.dto.UserDTO;
import com.simbirsoft.javaexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Получение юзеров по id в формате xml,json
     *
     * @param id
     * @return Возвращает список юзеров с определенным DTO, если DTO не указан, то
     * возвращает список всех юзеров
     */
    @GetMapping(value = "/user/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<UserDTO>> getUser(@PathVariable(name = "id") Integer id,@RequestBody UserDTO userDTO) {
        return userDTO == null ? ResponseEntity.ok(userService.getUsers()) : ResponseEntity.ok(userService.getUser(userDTO));
    }
//----------------------------------------------------------------------------------------------

    /**
     * Добавление юзера в БД
     *
     * @param user обьект который нужно добавить
     * @return Возращает Http статус и сообщение о статусе операции
     */
    @PostMapping("/user")
    public ResponseEntity addUser(@RequestBody UserDTO user) {
        boolean result = userService.addUser(user);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageDTO("Сервер не отвечает!"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO("Пользователь добавлен!"));

    }

    /**
     * Удаление юзера из БД
     *
     * @param id ИД который нужно удалить
     * @return Возращает Http статус и сообщение о статусе операции
     */
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id") Integer id) {
        boolean result = userService.deleteUser(id);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageDTO("Сервер не отвечает!"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO("Пользователь удален!"));
    }

    /**
     * Обновление данных юзера в БД
     *
     * @param user обьект пользователя которого нужно обновить
     * @return Возращает Http статус и сообщение о статусе операции
     */
    @PutMapping(value = "/user/{id}")
    public ResponseEntity updateUser(@RequestBody UserDTO user) {
        boolean result = userService.updateUser(user);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageDTO("Сервер не отвечает!"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new MessageDTO("Данные пользователя обновлены!"));
    }

}
