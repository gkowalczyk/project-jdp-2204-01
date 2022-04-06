package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Groups;
import com.kodilla.ecommercee.dto.GroupsDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class GroupsService {

    private final Map<Long, Groups> groups = new HashMap<>();

    public List<GroupsDto> getAllGroups() {
        return GroupMapper.mapToGroupsDtoList(groups);
    }

    public void createGroup(GroupsDto groupsDto) {
        Groups newGroup = new Groups(groupsDto.getId(), groupsDto.getGroupName());
        groups.put(groupsDto.getId(), newGroup);

    }

    public void editGroups(Long id, GroupsDto groupsDto) {
        Groups group = groups.get(id);
        group.setGroupName(groupsDto.getGroupName());
        groups.put(id, group);
    }

    public void deleteGroup(Long id) {
        groups.remove(id);
    }

    public GroupsDto getGroup(Long id) {
        Groups group = groups.get(id);
        if (group != null) {
            return GroupMapper.mapToGroupsDto(group);
        } else {
            return null;
        }
    }
}
