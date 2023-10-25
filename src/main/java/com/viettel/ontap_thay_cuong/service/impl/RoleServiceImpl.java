package com.viettel.ontap_thay_cuong.service.impl;

import com.viettel.ontap_thay_cuong.entities.Role;
import com.viettel.ontap_thay_cuong.repository.RoleRepositoryJpa;
import com.viettel.ontap_thay_cuong.service.RoleService;
import com.viettel.ontap_thay_cuong.service.dto.RoleDTO;
import com.viettel.ontap_thay_cuong.service.mapper.RoleMapper;
import com.viettel.ontap_thay_cuong.utils.Constants;
import com.viettel.ontap_thay_cuong.utils.ErrorApps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepositoryJpa roleRepositoryJpa;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleDTO> getAllActiveRoles(short active) {
        return roleMapper.toDTO(roleRepositoryJpa.findAllByStatus(active));
    }

    @Override
    public void addRole(RoleDTO roleDTO) throws Exception {
        String role = roleDTO.getRole();
        if (roleRepositoryJpa.findAllByRoleAndStatus(role, Constants.Status.ACTIVE).isPresent()){
            throw new Exception(ErrorApps.OBJECT_CAN_NOT_BE_NULL.getMessage());
        }

        Role roleEntity = roleMapper.toEntity(roleDTO);
        roleEntity.setStatus(Constants.Status.ACTIVE);
        roleRepositoryJpa.save(roleEntity);
    }
}
