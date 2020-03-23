package com.simbirsoft.javaexample.controller;

import com.simbirsoft.javaexample.dto.*;
import com.simbirsoft.javaexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
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
    private MessageSource messageSource;

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
    public ResponseEntity getUserPassport(LocaleContextHolder localeContext) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return ResponseEntity.status(HttpStatus.OK).body(userService.getPassport(userDetail.getUsername()));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("error.resource.unavailable",null,localeContext.getLocale()));
    }

    /**
     * Показывает для авторизованного юзера его кредиты
     *
     * @return кредит текущего юзера
     */
    @GetMapping(value = "/credit")
    public ResponseEntity getUserCredit(LocaleContextHolder localeContext) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return ResponseEntity.status(HttpStatus.OK).body(userService.getCredit(userDetail.getUsername()));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("error.resource.unavailable",null,localeContext.getLocale()));
    }

}
