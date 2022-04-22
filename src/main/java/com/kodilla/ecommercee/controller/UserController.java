package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/v1/user")
@Validated
public class UserController {


    @RequestMapping(method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userDto);

    }
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<UserDto> blockUserId(@RequestBody UserDto userDto)  {
        userDto.setActive(false);
        return ResponseEntity.ok(userDto);

    }
    @RequestMapping(method = RequestMethod.GET, value = "token/{userId}")
    public  ResponseEntity<String> getToken(@PathVariable Long userId) {
        Random random = new Random();
        String tokenUserKey = String.valueOf(random.nextInt(99999999)) ;
        return ResponseEntity.ok("Your personal key for userID:" + userId + "=" + tokenUserKey);
    }
}