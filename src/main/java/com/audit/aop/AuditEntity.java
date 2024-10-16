package com.audit.aop;

import com.audit.enums.AuditOperationsEnum;
import com.audit.models.orm.BaseEntity;

public class AuditEntity extends BaseEntity {

    private AuditOperationsEnum operation;

    public void setOperation(AuditOperationsEnum operation) {
	this.operation = operation;
    }

    public AuditOperationsEnum getOperation() {
	return operation;
    }

}
