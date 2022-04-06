package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupMapper {
    public static GroupDto mapToGroupsDto(final Group group) {
        return new GroupDto(
                group.getId(),
                group.getGroupName());
    }

    public static List<GroupDto> mapToGroupsDtoList(final Map<Long, Group> groupsList) {
        List<Group> collect = new ArrayList<>(groupsList.values());

        return collect.stream()
                .map(GroupMapper::mapToGroupsDto)
                .collect(Collectors.toList());
    }
}