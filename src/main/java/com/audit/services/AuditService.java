package com.audit.services;

import com.audit.exceptions.BusinessException;
import com.audit.models.dto.AuditLogRequestDto;

public interface AuditService {

    public Long insertAuditLog(AuditLogRequestDto auditLogRequestDto) throws BusinessException;

}
