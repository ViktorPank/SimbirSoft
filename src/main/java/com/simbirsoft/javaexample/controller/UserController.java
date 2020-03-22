package com.simbirsoft.javaexample.controller;

import com.simbirsoft.javaexample.dto.*;
import com.simbirsoft.javaexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Контроллер для запросов от авторизованных и неавторизованных юзеров
 */
@RestController
public class UserController {

    private UserService userService;
    public MessageSource messageSource;

    @Autowired
    public UserController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    /**
     * Показывает для авторизованного юзера его паспорт
     *
     * @return паспорт текущего юзера
     */
    @GetMapping(value = "/passport")
    public ResponseEntity<PassportDTO> getUserPassport() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return ResponseEntity.status(HttpStatus.OK).body(userService.getPassport(userDetail.getUsername()));
        }
        //не  понимаю что возвращать в случае ошибки, там же тип определенный
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("error.resource.unavailable",null,localeContext.getLocale()));
        return null;
    }

    /**
     * Показывает для авторизованного юзера его кредиты
     *
     * @return кредит текущего юзера
     */
    @GetMapping(value = "/credit")
    public ResponseEntity<List<CreditDTO>> getUserCredit() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return ResponseEntity.status(HttpStatus.OK).body(userService.getCredit(userDetail.getUsername()));
        }
        //не знаю что возвращать в случае ошибки, там же тип определенный
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("error.resource.unavailable",null,localeContext.getLocale()));

        return null;
    }

}
