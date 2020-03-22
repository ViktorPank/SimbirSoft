package com.simbirsoft.javaexample.controller;

import com.simbirsoft.javaexample.dto.PersonDTO;
import com.simbirsoft.javaexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
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
    public MessageSource messageSource;

    @Autowired
    public AdminController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    /**
     * Получение юзеров из БД
     *
     * @return Лист юзеров
     */
    @GetMapping(value = "/admin", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PersonDTO>> userList() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    /**
     * Добавление юзера в БД
     *
     * @param user          обьект который нужно добавить
     * @param localeContext Контекст локализации
     * @return Возращает Http статус и сообщение о статусе операции
     */
    @PostMapping(value = "/admin", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity addUser(@RequestBody PersonDTO user, LocaleContext localeContext) {
        boolean result = userService.addUser(user);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("error.resource.unavailable", null, localeContext.getLocale()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("request.ok", null, localeContext.getLocale()));
    }

    /**
     * Обновление юзера в БД
     *
     * @param user          обьект пользователя которого нужно обновить
     * @param localeContext Контекст локализации
     * @return Возращает Http статус и сообщение о статусе операции
     */
    @PutMapping(value = "/admin", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity updateUser(@RequestBody PersonDTO user, LocaleContext localeContext) {
        boolean result = userService.updateUser(user);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("error.resource.unavailable", null, localeContext.getLocale()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("put.request", null, localeContext.getLocale()));
    }

    /**
     * Удаление юзера из БД
     *
     * @param user          юзер которого нужно удалить
     * @param localeContext Контекст локализации
     * @return Возращает Http статус и сообщение о статусе операции
     */
    @DeleteMapping(value = "/admin", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity deleteUser(@RequestBody PersonDTO user, LocaleContext localeContext) {
        boolean result = userService.deleteUser(user);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("error.resource.unavailable", null, localeContext.getLocale()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("delete.request", null, localeContext.getLocale()));
    }
}
