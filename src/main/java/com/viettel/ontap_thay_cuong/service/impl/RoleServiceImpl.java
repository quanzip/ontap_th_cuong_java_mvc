package com.viettel.ontap_thay_cuong.service.impl;

import com.viettel.ontap_thay_cuong.entities.Role;
import com.viettel.ontap_thay_cuong.entities.UserEntity;
import com.viettel.ontap_thay_cuong.entities.UserRoleEntity;
import com.viettel.ontap_thay_cuong.repository.RoleRepositoryJpa;
import com.viettel.ontap_thay_cuong.repository.UserRepositoryJpa;
import com.viettel.ontap_thay_cuong.repository.UserRoleRepository;
import com.viettel.ontap_thay_cuong.service.RoleService;
import com.viettel.ontap_thay_cuong.service.dto.RoleDTO;
import com.viettel.ontap_thay_cuong.service.dto.UserRoleDTO;
import com.viettel.ontap_thay_cuong.service.mapper.RoleMapper;
import com.viettel.ontap_thay_cuong.service.mapper.UserRoleMapper;
import com.viettel.ontap_thay_cuong.utils.Constants;
import com.viettel.ontap_thay_cuong.utils.ErrorApps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepositoryJpa roleRepositoryJpa;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepositoryJpa userRepository;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<RoleDTO> getAllActiveRoles(short active) {
        return roleMapper.toDTO(roleRepositoryJpa.findAllByStatus(active));
    }

    @Override
    public void  addRole(RoleDTO roleDTO) throws Exception {
        String role = roleDTO.getRole();
        List<Role> roleOpt = roleRepositoryJpa.findAllByRoleAndStatus(role, Constants.Status.ACTIVE);
        if (!roleOpt.isEmpty()){
            throw new Exception(ErrorApps.ROLE_EXISTED.getMessage());
        }

        Role roleEntity = roleMapper.toEntity(roleDTO);
        roleEntity.setStatus(Constants.Status.ACTIVE);
        roleRepositoryJpa.save(roleEntity);

        List<UserRoleEntity> userRoleEntities = new ArrayList<UserRoleEntity>();
        final Consumer<? super List<Long>> userRoleConusmer = userIds -> {
            for (Long userId : userIds) {
                UserEntity userEntity = userRepository.findByIdAndStatus(userId, Constants.Status.ACTIVE).get();
                UserRoleEntity userRoleEntity = new UserRoleEntity();
                userRoleEntity.setRole(roleEntity);
                userRoleEntity.setUserEntity(userEntity);
                userRoleEntities.add(userRoleEntity);
            }
        };
        Optional.ofNullable(roleDTO.getUserIds()).ifPresent(userRoleConusmer);
        userRoleRepository.saveAll(userRoleEntities);
    }

    @Override
    public void deleteByUserId(Long userId) {
        userRoleRepository.findAllByUserEntity_id(userId).ifPresent(roles -> {
            userRoleRepository.deleteAll(roles);
        });

        logger.info("Deleted roles by userId: " + userId);
    }

    @Override
    public List<UserRoleDTO> getRoleForUserId(long userId) {
        List<UserRoleEntity> userList = this.userRoleRepository.findAllByUserEntity_id(userId).orElse(Collections.EMPTY_LIST);
        Function<UserRoleEntity, UserRoleDTO> entityToDTOF = entity -> userRoleMapper.toDTO(entity);
        return userList.stream().map(entityToDTOF).collect(Collectors.toList());
    }

    @Override
    public void deleteByRoleId(long roleId) {




        List<UserRoleEntity> userRoleEntities = userRoleRepository.findByRoleId(roleId);
        userRoleRepository.deleteAll(userRoleEntities);
        this.roleRepositoryJpa.findById(roleId).ifPresent(role -> roleRepositoryJpa.delete(role));
    }
}
