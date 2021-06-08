package com.sachet.userservice.dto;

public class Department {

    private Long id;
    private String departmentName;
    private Address departmentAddress;
    private String departmentCode;

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
}
