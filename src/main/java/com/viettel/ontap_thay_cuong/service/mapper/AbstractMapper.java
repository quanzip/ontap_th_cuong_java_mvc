package com.viettel.ontap_thay_cuong.service.mapper;

import java.util.List;

public interface AbstractMapper <D, E>{
    D toDTO(E e);
    List<D> toDTO(List<E> e);

    E toEntity(D d);
    List<E> toEntity(List<D> d);
}
