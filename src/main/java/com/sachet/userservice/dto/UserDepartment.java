package com.sachet.userservice.dto;

import com.sachet.userservice.entity.User;

public class UserDepartment {

    private User user;
    private Department userDepartment;

    public UserDepartment(User user, Department userDepartment) {
        this.user = user;
        this.userDepartment = userDepartment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Department getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(Department userDepartment) {
        this.userDepartment = userDepartment;
    }
}
