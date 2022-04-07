package com.kodilla.ecommercee;

import com.kodilla.ecommercee.UserNotFoundException;
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
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final UserMapper userMapper;


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "blockUserById")
    public ResponseEntity<Void> blockUserId(@RequestParam Long userId) throws UserNotFoundException {
        userMapper.mapToUserDto(userService.blockUserId(userId));
        return ResponseEntity.ok().build();

    }

    @RequestMapping(method = RequestMethod.PUT, value = "generateKey")
    public ResponseEntity<UserDto> generateTemporaryKey(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User saveKey = userService.generateKey(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(saveKey));
    }
}

