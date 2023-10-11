package com.viettel.ontap_thay_cuong.service;

import com.viettel.ontap_thay_cuong.entities.DepartmentEntity;
import com.viettel.ontap_thay_cuong.service.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentEntity> getAllDepartmentByStatus(short status);
    void addDepartment(DepartmentDTO departmentDTO) throws Exception;
    Object deleteDepartmentById(long id) throws Exception;
    Object updateDepartment(DepartmentDTO departmentDTO) throws Exception;
}
