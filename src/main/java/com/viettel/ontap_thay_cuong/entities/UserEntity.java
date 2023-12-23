package com.viettel.ontap_thay_cuong.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "UserEntity")
public class UserEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    DepartmentEntity department;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Trong lúc thêm mới 1 user, ta truyền 1 list các role ID vào để
    // set role cho user mới này, nhưng những role này đã tồn tại sẵnS trong bảng role rồi
    // nên nếu dùng loại CascadeType.all (user mới đc thêm và đồng thời thêm các role khi chúng chưa đc thêm) thì sẽ gây lỗi
    // do  những role ID đó đã tồn tại rồi mà ta vẫn cố thêm nữa, thay vì dùng loại CascadeType.ALL,
    // ta dùng CascadeType.Merge thì Db sẽ hiểu ta sẽ thêm mới 1 user và merge những roleId đó đã tồn tại cho user mới này.
    @ManyToMany()
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Role> roles;

    private String name;
    private String address;
    private Integer age;
    private Short status;
    private String imgPath;
    private String birthDate;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
}
