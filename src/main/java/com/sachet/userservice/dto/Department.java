package com.sachet.userservice.dto;

import com.sachet.userservice.entity.Events;

import java.util.List;

public class Department {

    private Long id;
    private String departmentName;
    private Address departmentAddress;
    private String departmentCode;
    List<Events> events;

    public Department() {
    }

    public Department(Long id, String departmentName, Address departmentAddress, String departmentCode) {
        this.id = id;
        this.departmentName = departmentName;
        this.departmentAddress = departmentAddress;
        this.departmentCode = departmentCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Address getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(Address departmentAddress) {
        this.departmentAddress = departmentAddress;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", departmentAddress=" + departmentAddress +
                ", departmentCode='" + departmentCode + '\'' +
                '}';
    }
}
