package com.simbirsoft.javaexample;

import com.simbirsoft.javaexample.data.Passport;
import com.simbirsoft.javaexample.data.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JavaExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaExampleApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("1234"));
    }
}
