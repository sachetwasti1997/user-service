package com.sachet.userservice.service;

import com.sachet.userservice.custom_error.UserNotFoundException;
import com.sachet.userservice.dto.Department;
import com.sachet.userservice.dto.UserDepartment;
import com.sachet.userservice.entity.User;
import com.sachet.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            throw new UserNotFoundException("User with given Id not found");
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public UserDepartment getUserWithDepartment(Long userId) throws UserNotFoundException{
        User user = findById(userId);
        String department_url = "http://DEPARTMENT-SERVICE/department/"+user.getDepartmentId();
        Department department = restTemplate.getForObject(department_url, Department.class);
        return new UserDepartment(user, department);
    }
}
