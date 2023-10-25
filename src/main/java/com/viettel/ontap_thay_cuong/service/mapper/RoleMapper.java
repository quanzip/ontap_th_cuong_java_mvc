package com.viettel.ontap_thay_cuong.service.mapper;

import com.viettel.ontap_thay_cuong.entities.Role;
import com.viettel.ontap_thay_cuong.service.dto.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface RoleMapper extends AbstractMapper<RoleDTO, Role> {

}
