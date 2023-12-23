package com.viettel.ontap_thay_cuong.service.mapper;

import com.viettel.ontap_thay_cuong.entities.UserRoleEntity;
import com.viettel.ontap_thay_cuong.service.dto.UserRoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring", uses = {RoleMapper.class, UserMapper.class})
public interface UserRoleMapper extends AbstractMapper<UserRoleDTO, UserRoleEntity> {

    @Mappings(value = {@Mapping(source = "userEntity", target = "userDTO"),
            @Mapping(source = "role", target = "roleDTO")})
    UserRoleDTO toDTO(UserRoleEntity en);

}
