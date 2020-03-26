package com.simbirsoft.javaexample.controller;

import com.simbirsoft.javaexample.dto.PersonDTO;
import com.simbirsoft.javaexample.service.UserService;
import com.simbirsoft.javaexample.util.MessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер запросов админа
 */
@RestController
public class AdminController {

    private UserService userService;
    private MessageStatus messageStatus;

    @Autowired
    public AdminController(UserService userService, MessageStatus messageStatus) {
        this.userService = userService;
        this.messageStatus = messageStatus;
    }

    /**
     * Получение юзеров из БД
     *
     * @return Лист юзеров
     */
    @GetMapping(value = "/admin")
    public ResponseEntity<List<PersonDTO>> userList() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    /**
     * Добавление юзера в БД
     *
     * @param user          обьект который нужно добавить
     * @return Возращает Http статус и сообщение о статусе операции
     */
    @PostMapping(value = "/admin", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity addUser(@RequestBody PersonDTO user) {
        boolean result = userService.addUser(user);
        if (!result) {
            return messageStatus.getMessageErrorStatus("error.resource.unavailable");
        }
        return messageStatus.getMessageOkStatus("request.ok");
    }

    /**
     * Обновление юзера в БД
     *
     * @param user          обьект пользователя которого нужно обновить
     * @return Возращает Http статус и сообщение о статусе операции
     */
    @PutMapping(value = "/admin", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity updateUser(@RequestBody PersonDTO user) {
        boolean result = userService.updateUser(user);
        if (!result) {
            return messageStatus.getMessageErrorStatus("error.resource.unavailable");
        }
        return messageStatus.getMessageOkStatus("put.request");
    }

    /**
     * Удаление юзера из БД
     *
     * @param user          юзер которого нужно удалить
     * @return Возращает Http статус и сообщение о статусе операции
     */
    @DeleteMapping(value = "/admin", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity deleteUser(@RequestBody PersonDTO user) {
        boolean result = userService.deleteUser(user);
        if (!result) {
            return messageStatus.getMessageErrorStatus("error.resource.unavailable");
        }
        return messageStatus.getMessageOkStatus("delete.request");
    }
}
