package com.viettel.ontap_thay_cuong.repository;

import com.viettel.ontap_thay_cuong.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByStatus(short status);
    Optional<UserEntity> findByIdAndStatus(Long id, short status);
    List<UserEntity> findAllByStatusAndDepartmentIsNull(short status);
}
