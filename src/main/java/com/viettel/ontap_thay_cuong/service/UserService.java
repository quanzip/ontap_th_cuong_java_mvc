package com.viettel.ontap_thay_cuong.service;

import com.viettel.ontap_thay_cuong.entities.UserEntity;
import com.viettel.ontap_thay_cuong.service.dto.UserDTO;

import java.util.List;

public interface UserService {
    Object saveUser(UserDTO userDTO);
    List<UserEntity> getAllUser();
    Object editUser(UserDTO userDTO) throws Exception;
    Object deleteUser(Long userId);
    UserDTO getStudentByIdAndStatus(long id, short status) throws Exception;

    List<UserEntity> getAllAvailableUser();
}
