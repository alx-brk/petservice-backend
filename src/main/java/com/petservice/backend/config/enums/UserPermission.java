package com.petservice.backend.config.enums;

public enum UserPermission {
    IMAGE_WRITE("image:write"),
    JOB_READ("job:read"),
    JOB_WRITE("job:write"),
    PROFILE_WRITE("profile:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
