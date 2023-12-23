package com.viettel.ontap_thay_cuong.repository;

import com.viettel.ontap_thay_cuong.entities.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    Optional<List<UserRoleEntity>> findAllByUserEntity_id(long id);

    List<UserRoleEntity> findByRoleId(long roleId);
}
