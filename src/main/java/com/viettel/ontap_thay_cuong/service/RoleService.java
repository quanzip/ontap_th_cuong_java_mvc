package com.viettel.ontap_thay_cuong.service;

import com.viettel.ontap_thay_cuong.service.dto.RoleDTO;
import com.viettel.ontap_thay_cuong.service.dto.UserRoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllActiveRoles(short active);

    void addRole(RoleDTO roleDTO) throws Exception;

    void deleteByUserId(Long userId);

    List<UserRoleDTO> getRoleForUserId(long userId);

    void deleteByRoleId(long roleId);
}
