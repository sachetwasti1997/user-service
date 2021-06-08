package com.sachet.userservice.service;

import com.sachet.userservice.custom_error.UserNotFoundException;
import com.sachet.userservice.dto.UserDepartment;
import com.sachet.userservice.entity.User;

public interface UserService {

    User save(User user);

    User findById(Long id) throws UserNotFoundException;

    User findByEmail(String email);

    void deleteAll();

    UserDepartment getUserWithDepartment(Long userId) throws UserNotFoundException;

}
