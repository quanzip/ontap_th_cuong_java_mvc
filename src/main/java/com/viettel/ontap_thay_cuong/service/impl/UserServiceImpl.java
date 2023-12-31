package com.viettel.ontap_thay_cuong.service.impl;

import com.viettel.ontap_thay_cuong.entities.Role;
import com.viettel.ontap_thay_cuong.entities.UserEntity;
import com.viettel.ontap_thay_cuong.repository.RoleRepositoryJpa;
import com.viettel.ontap_thay_cuong.repository.UserRepositoryJpa;
import com.viettel.ontap_thay_cuong.service.UserService;
import com.viettel.ontap_thay_cuong.service.dto.RoleDTO;
import com.viettel.ontap_thay_cuong.service.dto.UserDTO;
import com.viettel.ontap_thay_cuong.service.mapper.RoleMapper;
import com.viettel.ontap_thay_cuong.service.mapper.UserMapper;
import com.viettel.ontap_thay_cuong.utils.Constants;
import com.viettel.ontap_thay_cuong.utils.ErrorApps;
import com.viettel.ontap_thay_cuong.utils.Utils;
import org.mapstruct.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleRepositoryJpa roleRepositoryJPA;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional
    public Object saveUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.toEntity(userDTO);
        if (userEntity != null) {
            userEntity.setStatus(Constants.Status.ACTIVE);
            List<Long> roleDTOS = userDTO.getRoleIds();
            if (roleDTOS != null) {
                final Function<Long, Role> idToEntity = id -> {
                    Role role = new Role();
                    role.setId(id);
                    return role;
                };
                List<Role> roles = roleDTOS.stream().map(idToEntity).collect(Collectors.toList());
                userEntity.setRoles(roles);
            }

            MultipartFile file = userDTO.getMultipartFile();
            String fileName;
            if (file != null && (fileName = file.getOriginalFilename()) != null) {
                fileName = UUID.randomUUID().toString() + "_" + fileName;
                userEntity.setImgPath(fileName);
                userRepositoryJpa.save(userEntity);

                String storageFolder = Utils.getStorageFolder();
                Utils.saveFileToFolder(file, storageFolder, fileName);
            } else {
                userRepositoryJpa.save(userEntity);
            }
        }
        logger.info("Saved user successfully!");
        return userEntity;
    }

    @Override
    public List<UserEntity> getAllUser() {
        return userRepositoryJpa.findAllByStatus(Constants.Status.ACTIVE);
    }

    @Override
    public List<UserEntity> getAllAvailableUser() {
        return userRepositoryJpa.findAllByStatusAndDepartmentIsNull(Constants.Status.ACTIVE);
    }

    @Override
    public Object editUser(UserDTO userDTO) throws Exception {
        Long userId;
        if (Objects.isNull(userDTO) || (userId = userDTO.getId()) == null) {
            throw new Exception("User passed is not valid by ID");
        }

        UserEntity oldEntity = userRepositoryJpa.findByIdAndStatus(userId, Constants.Status.ACTIVE).orElseThrow(() -> new Exception(ErrorApps.ENTITY_NOT_FOUND.getMessage()));
        String oldImage = oldEntity.getImgPath();

        MultipartFile file = userDTO.getMultipartFile();
        String newImgFileName;
        String storageFolder = Utils.getStorageFolder();
        UserEntity newEntity;
        if (file != null && (newImgFileName = file.getOriginalFilename()) != null && !file.getOriginalFilename().isEmpty()) {
            newImgFileName = UUID.randomUUID().toString() + "_" + newImgFileName;
            userDTO.setImgPath(newImgFileName);

            oldEntity.setName(userDTO.getName());
            oldEntity.setAddress(userDTO.getAddress());
            oldEntity.setAge(userDTO.getAge());
            oldEntity.setImgPath(userDTO.getImgPath());
            oldEntity.setBirthDate(userDTO.getBirthDate());
            userRepositoryJpa.save(oldEntity);
            // only when saved user done then update image im folder
            Utils.saveFileToFolder(file, storageFolder, newImgFileName);

            if (!Objects.isNull(oldImage) && !oldImage.isEmpty()) {
                Utils.deleteFile(oldImage, storageFolder);
            }
        } else {
            oldEntity.setName(userDTO.getName());
            oldEntity.setAddress(userDTO.getAddress());
            oldEntity.setAge(userDTO.getAge());
            oldEntity.setImgPath(userDTO.getImgPath());
            oldEntity.setBirthDate(userDTO.getBirthDate());
            userRepositoryJpa.save(oldEntity);
        }
        return oldEntity;
    }

    @Override
    @Transactional
    public Object deleteUser(Long userId) {
        userRepositoryJpa.findByIdAndStatus(userId, Constants.Status.ACTIVE)
                .ifPresent(userEntity1 -> {
                    userEntity1.setStatus(Constants.Status.INACTIVE);
                    userRepositoryJpa.save(userEntity1);
                });
        return "OK";
    }

    @Override
    public UserDTO getStudentByIdAndStatus(long id, short status) throws Exception {
        return userMapper.toDTO(userRepositoryJpa.findByIdAndStatus(id, status).orElseThrow(() -> new Exception(ErrorApps.ENTITY_NOT_FOUND.getMessage())));
    }
}
