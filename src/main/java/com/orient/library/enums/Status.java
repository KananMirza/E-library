package com.orient.library.enums;

public enum Status {
    ACTIVE(1),DEACTIVE(0);
    private Integer type;

    Status(Integer type) {
        this.type = type;
    }

    public Integer value() {
        return type;
    }
}
