package com.viettel.ontap_thay_cuong.service;

import com.viettel.ontap_thay_cuong.entities.DepartmentEntity;
import com.viettel.ontap_thay_cuong.entities.UserEntity;
import com.viettel.ontap_thay_cuong.repository.DepartmentRepository;
import com.viettel.ontap_thay_cuong.service.dto.DepartmentDTO;
import com.viettel.ontap_thay_cuong.utils.Constants;
import com.viettel.ontap_thay_cuong.utils.ErrorApps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;
    private final MapstrucMapper mapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, MapstrucMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    @Override
    public Object getAllDepartmentByStatus(short status) {
        logger.info("Getting all Departments...");
        return departmentRepository.findAllByStatus(Constants.Status.ACTIVE);
    }

    @Override
    public Object addDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = mapper.toDepartmentEntity(departmentDTO);
        departmentEntity.setStatus(Constants.Status.ACTIVE);
        logger.info("Add Departments...");
        return departmentRepository.save(departmentEntity);
    }

    @Override
    public Object deleteDepartmentById(long id) throws Exception {
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElseThrow(()-> new Exception(ErrorApps.ENTITY_NOT_FOUND.getMessage()));
        departmentEntity.setStatus(Constants.Status.INACTIVE);
        logger.info("Delete Departments..." + id);
        departmentRepository.save(departmentEntity);
        return departmentEntity;
    }

    @Override
    public Object updateDepartment(DepartmentDTO departmentDTO) throws Exception {
        Long id = departmentDTO.getId();
        if (Objects.isNull(id)) {
            throw new Exception(ErrorApps.OBJECT_CAN_NOT_BE_NULL.getMessage());
        }
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElseThrow(()-> new Exception(ErrorApps.ENTITY_NOT_FOUND.getMessage()));
        departmentEntity.setName(departmentDTO.getName());
        departmentRepository.save(departmentEntity);
        return departmentEntity;
    }
}