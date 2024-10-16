package com.audit.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.audit.exceptions.BusinessException;
import com.audit.models.dto.AuditLogRequestDto;
import com.audit.models.orm.AuditLog;
import com.audit.repositories.AuditLogRepository;

@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    @Transactional(rollbackOn = BusinessException.class)
    public Long insertAuditLog(AuditLogRequestDto auditLogRequestDto) throws BusinessException {
	try {
	    System.out.println("auditLogRequestDto" + auditLogRequestDto);
	    AuditLog res = auditLogRepository.save(constructAuditLog(auditLogRequestDto));
	    return res.getId();
	} catch (Exception e) {
	    throw new BusinessException(e.getMessage());
	}
    }

    private AuditLog constructAuditLog(AuditLogRequestDto auditLogRequestDto) {
	AuditLog auditLog = new AuditLog();

	auditLog.setLogTable(auditLogRequestDto.getEntityName());
	auditLog.setOperationType(auditLogRequestDto.getOperation());
	auditLog.setLogRowId(auditLogRequestDto.getRowId());
	auditLog.setUserId(auditLogRequestDto.getUserId());
	auditLog.setOperationDate(new Date());
	auditLog.setMethodName(auditLogRequestDto.getMethodName());
	if (auditLogRequestDto.getRowData().length() <= 4000) {
	    auditLog.setLogRowDataAfter(auditLogRequestDto.getRowData());
	}

	return auditLog;
    }

}
