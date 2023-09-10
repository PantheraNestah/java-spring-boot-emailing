package com.SpringBootEmailing.Resource;

import com.SpringBootEmailing.entities.HttpResponse;
import com.SpringBootEmailing.entities.UserEntity;
import com.SpringBootEmailing.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class MainController {
    @Autowired
    private UserServiceImpl service;

    @PostMapping
    public ResponseEntity<HttpResponse> addUser(@RequestBody UserEntity user)
    {
        UserEntity newUser = service.saveUser(user);
        return (
                ResponseEntity.created(URI.create("")).body(
                        HttpResponse.builder()
                                .data(Map.of("user", newUser))
                                .message("New User Created")
                                .status(HttpStatus.CREATED)
                                .statusCode(HttpStatus.CREATED.value())
                                .build()

                )
                );
    }

    @GetMapping
    public ResponseEntity<HttpResponse> confirmUserAccount(@RequestParam("token") String token)
    {
        Boolean isSuccess = service.verifyToken(token);
        return (
                ResponseEntity.ok().body(
                        HttpResponse.builder()
                                .data(Map.of("Success", isSuccess))
                                .message("Account verification success")
                                .status(HttpStatus.OK)
                                .statusCode(HttpStatus.OK.value())
                                .build()

                )
        );
    }
}
