package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
public class GroupTestSuite {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    ProductRepository productRepository;


    private Group trousers;
    private Group hats;
    private List<Product> allProducts;

    @Before
    public void beforeEach() {
        trousers = new Group("trousers");
        hats = new Group("hats");
        trousers = groupRepository.save(trousers);
        hats = groupRepository.save(hats);
    }

    @After
    public void cleanUp() {
        List<Group> allGroups = groupRepository.findAll();
        groupRepository.deleteAll();
    }


    @Test
    public void testCreateNewGroup() {
        //Given
        Group newGroup = new Group("newGroup");
        //When
        groupRepository.save(newGroup);

        //Then

        List<Group> allGroups = groupRepository.findAll();
        assertEquals(3, allGroups.size());

    }

    @Test
    public void testFindGroupById() {
        //When
        List<Group> allGroups = groupRepository.findAll();
        Group newGroup = groupRepository.findById(1L).get();
        //Then
        assertNotNull(newGroup);
        assertEquals(1L, (long) newGroup.getId());
    }


    @Test
    public void testUpdateGroup() {
        //Given
        String newName = "updatedGroup";
        Group group1 = groupRepository.findById(2L).get();
        assertNotNull(group1);
        //When
        group1.setName(newName);

        group1 = groupRepository.findById(2L).get();
        //Then
        assertEquals(newName, group1.getName());

    }


    @Test
    public void testDeleteGroup() {
        //Given
        List<Group> allGroups = groupRepository.findAll();
        assertEquals(2, allGroups.size());

        //When
        groupRepository.deleteById(2L);
        allGroups = groupRepository.findAll();

        //Then
        assertEquals(1, allGroups.size());
    }
}

//    @Test
//    public void testProductStaysWhenGroupDeleted() {
//        //Given
//
//
//        Product product1 = new Product("Blue Jeans", "new cpllection", new BigDecimal(299.99), trousers);
//        Product product2 = new Product("Overalls", "tralalala ", new BigDecimal(99.99), trousers);
//        Product product3 = new Product("cap", "new cap", new BigDecimal(15.99), hats);
//
//
//        //When
//        groupRepository.delete(trousers);
//        allProducts = productRepository.findAll();
//
//
//        //Then
//        assertEquals(3, allProducts.size());

