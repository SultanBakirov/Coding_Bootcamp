package com.example.repository;

import com.example.models.Group;

import java.io.IOException;
import java.util.List;

public interface GroupRepository {

    List<Group> getAllGroups(Long id);

    void addGroup(Long id, Group group);

    Group getGroupById(Long id);

    void updateGroup(Group group, Long id);

    void deleteGroupById(Long id);

    void assignGroup(Long courseId, Long groupId) throws IOException;

}
