package com.viettel.ontap_thay_cuong.service.impl;

import com.viettel.ontap_thay_cuong.repository.RoleRepositoryJpa;
import com.viettel.ontap_thay_cuong.service.RoleService;
import com.viettel.ontap_thay_cuong.service.dto.RoleDTO;
import com.viettel.ontap_thay_cuong.service.mapper.RoleMapper;
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
}
