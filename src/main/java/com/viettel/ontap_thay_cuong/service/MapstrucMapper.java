package com.viettel.ontap_thay_cuong.service;

import com.viettel.ontap_thay_cuong.entities.DepartmentEntity;
import com.viettel.ontap_thay_cuong.entities.UserEntity;
import com.viettel.ontap_thay_cuong.service.dto.DepartmentDTO;
import com.viettel.ontap_thay_cuong.service.dto.UserDTO;
import com.viettel.ontap_thay_cuong.utils.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {Utils.class})
public interface MapstrucMapper {
    UserDTO toUserDTO(UserEntity userEntity);

    UserEntity toUserEntity(UserDTO userDTO);

    @Mapping(source = "userDTOS", target = "users", qualifiedByName = "stringIdToUser")
    DepartmentEntity toDepartmentEntity(DepartmentDTO departmentDTO);
}
