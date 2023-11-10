package com.orient.library.enums;


public enum Message {
    SUCCESS("Success!"),
    BAD_REQUEST("Something went wrong!"),
    CATEGORY_NOT_FOUND("Category not found!"),
    AUTHOR_NOT_FOUND("Author not found!"),
    LEASE_STATUS_NOT_FOUNT("LeaseStatus not found!"),
    PENALTY_TYPE_NOT_FOUND("PenaltyType not found!");

    private String message;
    Message(String message){
        this.message = message;
    }

    public String  value(){
        return this.message;
    }


}
