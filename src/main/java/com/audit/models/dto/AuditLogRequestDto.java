package com.audit.models.dto;

import com.audit.enums.AuditOperationsEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuditLogRequestDto {

    private String entityName;
    private AuditOperationsEnum operation;
    private String rowId;
    private String rowData;
    private String userId;
    private String methodName;

}
