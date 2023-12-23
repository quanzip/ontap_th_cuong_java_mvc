package com.viettel.ontap_thay_cuong.service.mapper;

import com.viettel.ontap_thay_cuong.entities.DepartmentEntity;
import com.viettel.ontap_thay_cuong.service.dto.DepartmentDTO;
import com.viettel.ontap_thay_cuong.utils.MapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MapperUtils.class})
public interface DepartmentMapper extends AbstractMapper<DepartmentDTO, DepartmentEntity> {

    @Mapping(source = "users", target = "userDTOS", qualifiedByName = "usersToIds")
    DepartmentDTO toDTO(DepartmentEntity departmentEntity);

    @Mapping(source = "userDTOS", target = "users", qualifiedByName = "stringIdToUser")
    DepartmentEntity toEntity(DepartmentDTO departmentDTO);
}
