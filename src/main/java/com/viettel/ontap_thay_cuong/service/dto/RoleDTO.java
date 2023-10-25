package com.viettel.ontap_thay_cuong.service.dto;

import java.util.List;

public class RoleDTO {
    private Long id;
    private String role;

    private List<UserDTO> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
