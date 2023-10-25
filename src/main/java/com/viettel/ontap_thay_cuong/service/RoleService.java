package com.viettel.ontap_thay_cuong.service;

import com.viettel.ontap_thay_cuong.service.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllActiveRoles(short active);
}
