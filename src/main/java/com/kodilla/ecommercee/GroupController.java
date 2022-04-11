package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupDBService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
@AllArgsConstructor
public class GroupController {

    private final GroupDBService groupDBService;
    private final GroupMapper GroupMapper;

    @GetMapping(value = "/all")
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<GroupDto> groupDtoList = new ArrayList<>();
        // to do service
        return ResponseEntity.ok(groupDtoList);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> addNewGroup(@RequestBody GroupDto groupDto) {
        // to do service
        return ResponseEntity.ok(groupDto);
    }

    @GetMapping(value = "/{groupId}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long groupId) {
        GroupDto groupDto = new GroupDto();
        // to do service
        return ResponseEntity.ok(groupDto);
    }

    @PutMapping(value = "/{groupId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Long groupId, @RequestBody GroupDto groupDto) {
        // to do service
        return ResponseEntity.ok(groupDto);
    }
}