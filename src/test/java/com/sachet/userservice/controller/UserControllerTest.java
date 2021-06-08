package com.sachet.userservice.controller;

import com.sachet.userservice.entity.User;
import com.sachet.userservice.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @Autowired
    UserService userService;
    @Autowired
    TestRestTemplate testRestTemplate;

    private <T>ResponseEntity<T> getUser(Long id, Class<T> response){
        String url = "/user/"+id;
        return testRestTemplate.getForEntity(
                url,
                response
        );
    }

    private <T> ResponseEntity<T> createUser(User user, Class<T> response){
        String url = "/user/create";
        return testRestTemplate.postForEntity(
                url,
                user,
                response
        );
    }

    private <T>ResponseEntity<T> getUserWithDepartment(Long id, Class<T> response){
        String url = "/user/"+id+"/department";
        return testRestTemplate.getForEntity(
                url,
                response
        );
    }

    private User createValidUser(){
        User user = new User();
        user.setFirstName("Sachet");
        user.setLastName("Wasti");
        user.setEmail("sachet@gmail.com");
        user.setDepartmentId(4L);

        return user;
    }

    @BeforeEach
    void beforeAll() {
        userService.deleteAll();
    }

    @Test
    void getUser_idNotExist_receiveNotFound(){
        ResponseEntity<?> responseEntity = getUser(1L, Object.class);
        System.out.println(responseEntity.getBody());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void getUser_userExists_receiveOk(){
        User user = createValidUser();
        userService.save(user);

        ResponseEntity<?> responseEntity = getUser(user.getId(), Object.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void createUser_userInvalid_receiveBadRequest(){
        User user = createValidUser();
        user.setEmail(null);
        user.setLastName(null);
        user.setFirstName(null);

        ResponseEntity<?> responseEntity = createUser(user, Object.class);
        System.out.println(responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void createUser_userWithEmailAlreadyExist_receiveBadRequest(){
        User user = createValidUser();
        userService.save(user);

        User userAnother = createValidUser();

        ResponseEntity<?> responseEntity = createUser(userAnother, Object.class);
        System.out.println(responseEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void createUser_userCreatedSuccessfully_receiveOk(){
        User user = createValidUser();

        ResponseEntity<?> responseEntity = createUser(user, Object.class);
        System.out.println(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getUserWithDepartment(){
        User user = createValidUser();
        userService.save(user);

        ResponseEntity<?> responseEntity = getUserWithDepartment(user.getId(), Object.class);
        System.out.println(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}


















