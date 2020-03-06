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

// TODO: Зачем полный путь? Переименнуй свой контроллер в UserController и юзай импорты
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private UserService userService;

    @Autowired
    public RestController(UserService userService) {
        this.userService = userService;
    }

    // TODO: Так документацию никто не пишет, если хочешь красиво, то пиши через /** со всеми параметрами
    //возращает всех юзеров

    // TODO: Оставь просто user, не стоит мешать множественное число и единственное
    @GetMapping("/users")
    // TODO: Неиспользующиеся параметры и лишний пробелы в теле метода
    public ResponseEntity<List<UserDTO>> getUsers(HttpServletResponse response, HttpServletRequest request) {

        return ResponseEntity.ok(userService.getUsers());

    }

    // TODO: Либо везде используй @Get/Post/Put/DeleteMapping, либо везде @RequestMapping + method = ...
    //Маппинг который принимает и возвращает json по id юзера
    @RequestMapping(value = "/user/{id}", produces = {"application/json"}, method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getDataJSON(@PathVariable(name = "id") Integer id) {

        return ResponseEntity.ok(userService.getSubject(id));
    }

    // TODO: В ответе заголовок, который сообщает браузеры в каком виде он вернул ответ ставится не через Accept, а через Content-Type
    //Маппинг который принимает и возвращает xml по id юзера
    @RequestMapping(value = "/user/{id}", produces = {"application/xhtml+xml"}, method = RequestMethod.GET)
    public ResponseEntity<String> getUserXML(HttpServletResponse response, @PathVariable(name = "id") Integer id) {

        response.setHeader(HttpHeaders.ACCEPT, "application/xhtml+xml");
        XStream xStream = new XStream();
        String xml = xStream.toXML(userService.getSubject(id));

        return ResponseEntity.ok(xml);
    }

    //Маппинг который принимает и возвращает данные по id юзера
    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserDTO>> getUser(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(userService.getSubject(id));

    }

    //Маппинг добавления юезра и возвращает статус Http
    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody UserDTO user) {
        boolean result = userService.addSubject(user);
        if (!result) {
            // TODO: Здесь и ниже по коду можно вызывать .body вместо .build и прокидывать еще объект с человекопонятным текстом ошибки
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    //Маппинг удаления юезра и возвращает статус Http
    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id") Integer id) {
        boolean result = userService.deleteUser(id);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //Маппинг обновления юезра и возвращает статус Http
    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity updateUser(@RequestBody UserDTO user) {
        boolean result = userService.updateUser(user);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
