package com.kodilla.ecommercee;

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
    @Autowired
    private final UserMapper userMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(method = RequestMethod.POST, value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.createUser(user);
        return ResponseEntity.ok().build();

    }
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<UserDto> blockUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User userBlocked = userService.blockUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(userBlocked));

    }
    @PostMapping("user")
    public ResponseEntity<User> generateToken(@RequestParam("user") String username, @RequestParam("password") String key) {
        String token = userService.getJWTToken(username);
        User user = new User();
        user.setUserName(username);
        user.setPersonalKey(token);
        return ResponseEntity.ok(user);
    }
}

