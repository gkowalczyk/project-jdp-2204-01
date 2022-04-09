package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void crudTest() {
        User user = new User(1L);
        repository.save(user);

        Optional<User> byId = repository.findById(1L);
        User user1 = byId.get();
        assertNotNull("User is null", user1);

        repository.delete(user1);
    }
}
