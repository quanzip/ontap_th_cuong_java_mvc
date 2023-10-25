package com.viettel.ontap_thay_cuong.repository;

import com.viettel.ontap_thay_cuong.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepositoryJpa extends JpaRepository<Role, Long> {
    List<Role> findAllByStatus(Short status);
}
