package com.viettel.ontap_thay_cuong.service.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.util.List;

public class UserDTO {
    private Long id;

    @NotBlank(message = "${validation.invalid-name-empty}")
    @Length(max = 20, message = "${validation.invalid-name-length}")
    private String name;

    private String address;

    @Max(value = 100)
    @Min(value = 1)
    private Integer age;

    @Range(min = 0, max = 1)
    private short status;

    private String imgPath;

    //    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss") // this is use for FE pass data to backend to Map
    @NotNull(message = "${validation.birth-date-null}")
    private String birthDate;

    private List<Long> roleIds;

    private MultipartFile multipartFile;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
