package com.viettel.ontap_thay_cuong.service.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class DepartmentDTO {
    Long id;
    String name;

    @DateTimeFormat(pattern = "dd/MM/YYYY")
    Date createAt;

    Date updateAt;

    List<String> userDTOS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public List<String> getUserDTOS() {
        return userDTOS;
    }

    public void setUserDTOS(List<String> userDTOS) {
        this.userDTOS = userDTOS;
    }
}
