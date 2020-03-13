package com.simbirsoft.javaexample.dto;

/**
 * Класс для работы с ответами сервера
 */
public class MessageDTO {
    // TODO: Модификатор доступа?
    String message;

    public MessageDTO() {
    }

    public MessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
