package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.GroupsDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
@AllArgsConstructor
public class GroupsController {

    //private final DbService dbService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<GroupsDto>> getAllGroups() {
        List<GroupsDto> groupDtoList = new ArrayList<>();
        // to do service
        return ResponseEntity.ok(groupDtoList);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupsDto> addNewGroup(@RequestBody GroupsDto groupDto) {
        // to do service
        return ResponseEntity.ok(groupDto);
    }

    @GetMapping(value = "/{groupId}")
    public ResponseEntity<GroupsDto> getGroup(@PathVariable Long groupId) {
        GroupsDto groupDto = new GroupsDto();
        // to do service
        return ResponseEntity.ok(groupDto);
    }

    @PutMapping(value = "/{groupId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupsDto> updateGroup(@PathVariable Long groupId, @RequestBody GroupsDto groupDto) {
        // to do service
        return ResponseEntity.ok(groupDto);
    }
}