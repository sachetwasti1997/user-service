package com.sachet.userservice.service;

import com.sachet.userservice.config.JmsConfig;
import com.sachet.userservice.custom_error.UserNotFoundException;
import com.sachet.userservice.dto.Department;
import com.sachet.userservice.dto.Events;
import com.sachet.userservice.dto.UserDepartment;
import com.sachet.userservice.entity.User;
import com.sachet.userservice.repository.UserRepository;
//import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final JmsTemplate jmsTemplate;
//    private final ActiveMQQueue queue;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate, JmsTemplate jmsTemplate/*, ActiveMQQueue queue*/) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.jmsTemplate = jmsTemplate;
//        this.queue = queue;
    }

    private Department findDepartmentById(Long id){
        String departmentUrl = "http://localhost:9001/department/"+id;
        return restTemplate.getForObject(departmentUrl, Department.class);
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
        String department_url = "http://localhost:9001/department/"+user.getDepartmentId();
        Department department = restTemplate.getForObject(department_url, Department.class);
        return new UserDepartment(user, department);
    }

    @Override
    public Department createEvent(Long departmentId, Events events) {
        Department department = findDepartmentById(departmentId);
        if (department.getEvents() == null){
            department.setEvents(new ArrayList<>());
        }
        department.getEvents().add(events);
        jmsTemplate.convertAndSend("event-request", events);
        return department;
    }

    @Scheduled(fixedRate = 5000)
    public void createEvent(){
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            System.out.println("----------------------------------------------------------------------------");
            Long departmentId = user.getDepartmentId();
            Department department = findDepartmentById(departmentId);
            System.out.println(department);
            Events events = new Events();
            events.setEventName("Class "+department.getDepartmentName());
            events.setEventStartTime(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(events.getEventStartTime());
            calendar.add(Calendar.HOUR, 1);
            events.setEventEndTime(calendar.getTime());
            events.setDepartment(department);
            if (department.getEvents() == null || department.getEvents().isEmpty()){
                jmsTemplate.convertAndSend("test-queue", events);
                System.out.println("Created event "+events);
            }
        });
    }
}






















