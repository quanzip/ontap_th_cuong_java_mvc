package com.viettel.ontap_thay_cuong.utils;

import com.viettel.ontap_thay_cuong.entities.UserEntity;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MapperUtils {
    @Named(value = "stringIdToUser")
    public static UserEntity stringIdToUser(String id) {
        if (Objects.isNull(id) || id.isEmpty()) return null;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(Long.parseLong(id));
        return userEntity;
    }

    @Named(value = "usersToIds")
    public static List<Long> userdToIds(List<UserEntity> userEntities) {
        if (userEntities.isEmpty()) return Collections.emptyList();
        Function<UserEntity, Long> getIdFromUserEntity = UserEntity::getId;
        return userEntities.stream().map(getIdFromUserEntity).collect(Collectors.toList());
    }
}
