package com.viettel.ontap_thay_cuong.service;

import com.viettel.ontap_thay_cuong.service.dto.DepartmentDTO;

public interface DepartmentService {
    Object getAllDepartmentByStatus(short status);
    Object addDepartment(DepartmentDTO departmentDTO);
    Object deleteDepartmentById(long id) throws Exception;
    Object updateDepartment(DepartmentDTO departmentDTO) throws Exception;
}
