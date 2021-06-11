package com.sachet.userservice.controller;

import com.sachet.userservice.custom_error.UserNotFoundException;
import com.sachet.userservice.entity.Events;
import com.sachet.userservice.entity.User;
import com.sachet.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @GetMapping("/{id}/department")
    public ResponseEntity<?> getUserWithDepartment(@PathVariable Long id) throws UserNotFoundException{
        return new ResponseEntity<>(userService.getUserWithDepartment(id), HttpStatus.OK);
    }

    @PostMapping("/event/{id}")
    public ResponseEntity<?> createEvent(@PathVariable Long id, @RequestBody Events events){
        return new ResponseEntity<>(userService.createEvent(id, events), HttpStatus.OK);
    }
}
