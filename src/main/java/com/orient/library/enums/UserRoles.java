package com.orient.library.enums;

public enum UserRoles {
    USER(1L), ADMIN(2L);

    private final Long roleId;

    UserRoles(Long roleId) {
        this.roleId = roleId;
    }

    public Long value() {
        return this.roleId;
    }
}
