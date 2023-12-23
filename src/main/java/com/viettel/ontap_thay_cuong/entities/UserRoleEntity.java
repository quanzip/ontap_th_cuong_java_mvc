package com.viettel.ontap_thay_cuong.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "userId")
    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity userEntity;

    @JoinColumn(name = "roleId")
    @OneToOne
    private Role role;

    public UserRoleEntity() {
    }
//
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
