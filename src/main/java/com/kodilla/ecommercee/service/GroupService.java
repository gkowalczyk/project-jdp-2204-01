package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class GroupService {

    private final Map<Long, Group> groups = new HashMap<>();

    public List<GroupDto> getAllGroups() {
        return GroupMapper.mapToGroupsDtoList(groups);
    }

    public void createGroup(GroupDto groupsDto) {
        Group newGroup = new Group(groupsDto.getId(), groupsDto.getGroupName());
        groups.put(groupsDto.getId(), newGroup);

    }

    public void editGroups(Long id, GroupDto groupsDto) {
        Group group = groups.get(id);
        group.setGroupName(groupsDto.getGroupName());
        groups.put(id, group);
    }

    public void deleteGroup(Long id) {
        groups.remove(id);
    }

    public GroupDto getGroup(Long id) {
        Group group = groups.get(id);
        if (group != null) {
            return GroupMapper.mapToGroupsDto(group);
        } else {
            return null;
        }
    }
}
