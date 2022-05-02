package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupDBService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
@AllArgsConstructor
public class GroupController {

    private final GroupDBService groupDBService;
    private final GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<Group> groups = groupDBService.getAllGroups();
        return ResponseEntity.ok(groupMapper.mapToGroupDtoList(groups));
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long groupId) throws GroupNotFoundException {

        return ResponseEntity.ok(groupMapper.mapToGroupDto(groupDBService.getGroupById(groupId)));
    }

    @PutMapping(value = "{groupId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Long groupId, @RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        Group updatedGroup = groupDBService.updateGroup(group, groupId);
        return ResponseEntity.ok(groupMapper.mapToGroupDto(updatedGroup));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> addNewGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        groupDBService.saveGroup(group);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) {
        groupDBService.deleteGroup(groupId);
        return ResponseEntity.ok().build();
    }
}