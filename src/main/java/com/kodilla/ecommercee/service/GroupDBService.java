package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class GroupDBService {
    private final GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id) throws GroupNotFoundException {
        return groupRepository.findById(id).orElseThrow(() -> new GroupNotFoundException(
                "Group for update not found for id: " + id));
    }

    public Group saveGroup(final Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
    public Group updateGroup(final Group group, final Long id) {
        Optional<Group> groupEntity = groupRepository.findById(id);
        Group groupForUpdate = groupEntity.orElseThrow(() -> new GroupNotFoundException(
                "Group for update not found for id: " + id));
        groupForUpdate.setName(group.getName());
        return groupRepository.save(groupForUpdate);
    }
}

