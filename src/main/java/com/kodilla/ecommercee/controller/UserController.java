package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping(value = "create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = UserMapper.mapUserDtoToUser(userDto);
        userService.createUser(user);
        return ResponseEntity.ok().build();

    }
    @PutMapping
    public ResponseEntity<UserDto> blockUser(@RequestBody UserDto userDto)  {
        User user = UserMapper.mapUserDtoToUser(userDto);
        User userBlocked = userService.blockUser(user);
        return ResponseEntity.ok(UserMapper.mapUserToUserDto(userBlocked));

    }
    @PostMapping("user")
    public ResponseEntity<Void> generateToken(@RequestParam("user") String username, @RequestParam("password") String key) {
        String token = userService.getJWTToken(key);
        userService.saveUserData(username, token);
        return ResponseEntity.ok().build();
    }
}

