package com.audit.enums;

public enum AuditOperationsEnum {

    INSERT(0),
    UPDATE(1),
    DELETE(2);

    private int code;

    private AuditOperationsEnum(int code) {
	this.code = code;
    }

    public int getCode() {
	return code;
    }

}
