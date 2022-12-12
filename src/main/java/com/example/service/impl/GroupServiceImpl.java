package com.example.service.impl;

import com.example.models.Group;
import com.example.repository.GroupRepository;
import com.example.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAllGroups(Long id) {
        return groupRepository.getAllGroups(id);
    }

    @Override
    public List<Group> getAllGroupsByCourseId(Long courseId) {
        return groupRepository.getAllGroupsByCourseId(courseId);
    }

    @Override
    public void addGroupByCourseId(Long id, Long courseId, Group group) {
        groupRepository.addGroupByCourseId(id, courseId, group);
    }

    @Override
    public void addGroup(Long id, Group group) {
        groupRepository.addGroup(id,group);
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.getGroupById(id);
    }

    @Override
    public void updateGroup(Group group, Long id) {
        groupRepository.updateGroup(group,id);
    }

    @Override
    public void deleteGroupById(Long id) {
        groupRepository.deleteGroupById(id);
    }

    @Override
    public void assignGroup(Long courseId, Long groupId) throws IOException {
        groupRepository.assignGroup(courseId, groupId);
    }
}
