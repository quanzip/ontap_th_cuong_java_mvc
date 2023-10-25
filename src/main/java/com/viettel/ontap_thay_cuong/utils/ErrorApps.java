package com.viettel.ontap_thay_cuong.utils;

public enum ErrorApps {
    ENTITY_NOT_FOUND(4004,I18n.getMessage("entity.not_found")),
    OBJECT_CAN_NOT_BE_NULL(4000,I18n.getMessage("object.can_not_be_null")),
    ROLE_EXISTED(4001,I18n.getMessage("role.existed"));

    private int status;
    private String message;

    ErrorApps(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
