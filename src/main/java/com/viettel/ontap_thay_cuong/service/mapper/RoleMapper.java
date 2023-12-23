package com.viettel.ontap_thay_cuong.service.mapper;

import com.viettel.ontap_thay_cuong.entities.Role;
import com.viettel.ontap_thay_cuong.service.dto.RoleDTO;
import com.viettel.ontap_thay_cuong.utils.MapperUtils;
import com.viettel.ontap_thay_cuong.utils.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Objects;







@Mapper(componentModel = "spring", uses = {MapperUtils.class})
public interface RoleMapper extends AbstractMapper<RoleDTO, Role> {
//    default Role fromId(Long id) {
//        if (Objects.isNull(id)) return null;
//        Role role = new Role();
//        role.setId(id);
//        return role;
//    }


    @Mapping(source = "users", target = "userIds", qualifiedByName = "usersToIds")
    RoleDTO toDTO(Role role);
}
