package com.kodilla.ecommercee.group;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
class GroupTestSuite {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    ProductRepository productRepository;

    private Group trousers;
    private Group hats;
    private List<Product> allProducts;

    @BeforeEach
    public void init() {
        groupRepository.deleteAll();
    }

    @Test
    public void testCreateNewGroup() {
        //Given
        trousers = new Group("trousers");
        hats = new Group("hats");
        trousers = groupRepository.save(trousers);
        hats = groupRepository.save(hats);
        Group newGroup = new Group("newGroup");
        //When
        groupRepository.save(newGroup);
        //Then
        List<Group> allGroups = groupRepository.findAll();
        assertEquals(3, allGroups.size());
        //cleanup
        deleteAll();
    }

    @Test
    public void testFindGroupById() {
        //When
        hats = new Group("hats");
        groupRepository.save(hats);
        Group newGroup = groupRepository.findById(hats.getId()).get();
        //Then
        assertNotNull(newGroup);
        assertEquals(hats.getId(), newGroup.getId());
        //clean up
        deleteAll();
    }

    @Test
    public void testUpdateGroup() {
        //Given
        hats = new Group("hats");
        groupRepository.save(hats);
        String newName = "updatedGroup";
        Group group1 = groupRepository.findById(hats.getId()).get();
        assertNotNull(group1);
        //When
        group1.setName(newName);
        group1 = groupRepository.findById(hats.getId()).get();
        //Then
        assertEquals(newName, group1.getName());
        //clean up
        deleteAll();
    }
    @Test
    public void testDeleteGroup() {
        //Given
        trousers = new Group("trousers");
        hats = new Group("hats");
        trousers = groupRepository.save(trousers);
        hats = groupRepository.save(hats);
        List<Group> allGroups = groupRepository.findAll();
        assertEquals(2, allGroups.size());
        //When
        groupRepository.deleteById(hats.getId());
        allGroups = groupRepository.findAll();
        //Then
        assertEquals(1, allGroups.size());
        //clean up
        deleteAll();
    }

    private void deleteAll() {
        groupRepository.deleteAll();
    }
}