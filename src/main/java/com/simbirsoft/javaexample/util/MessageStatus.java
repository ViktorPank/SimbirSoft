package com.simbirsoft.javaexample.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MessageStatus {

    private MessageSource messageStatus;

    public ResponseEntity getMessageErrorStatus(String message, LocaleContextHolder localeContext) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(messageStatus.getMessage(message, null, localeContext.getLocale()));

    }

    public ResponseEntity getMessageOkStatus(String message, LocaleContextHolder localeContext) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(messageStatus.getMessage(message, null, localeContext.getLocale()));
    }

}
