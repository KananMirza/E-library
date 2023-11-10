package com.orient.library.enums;

public enum DeleteType {
    DELETE(1),NONDELETE(0);
    private int type;

    DeleteType(int type) {
        this.type = type;
    }

    public int value() {
        return type;
    }
}
