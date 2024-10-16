package com.audit.aop;

import java.lang.reflect.Field;

import javax.persistence.Id;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.audit.enums.AuditOperationsEnum;
import com.audit.models.dto.AuditLogRequestDto;
import com.audit.services.AuditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class AuditAspect {

    private boolean isAuditing = false;

    @Autowired
    private AuditService auditService;

    private final Log logger = LogFactory.getLog(AuditAspect.class);

    @Before(value = "execution(* org.springframework.data.jpa.repository.JpaRepository+.save(..)) and args(entity)")
    public void beforeSave(JoinPoint joinPoint, AuditEntity entity) {
	try {
	    logger.info("Before Save: " + entity);

	    entity.setId(getRowId(entity));
	    if (entity.getId() == null) {
		entity.setOperation(AuditOperationsEnum.INSERT);
	    } else {
		entity.setOperation(AuditOperationsEnum.UPDATE);
	    }
	} catch (Exception e) {
	    logger.error(e);
	}
    }

    @AfterReturning(value = "execution(* org.springframework.data.jpa.repository.JpaRepository+.save(..)) and args(entity)")
    public void afterSave(JoinPoint joinPoint, AuditEntity entity) {
	if (!isAuditing) {
	    try {
		System.out.println("method name is " + joinPoint.getSignature() + "jouing point " + joinPoint);
		isAuditing = true; // Set flag to indicate auditing is in progress
		callAuditWS(constructAuditDto(entity, entity.getOperation(), joinPoint.getSignature().getName()));
	    } catch (Exception e) {
		logger.error(e);
	    } finally {
		isAuditing = false; // Reset the flag after the call
	    }
	}
    }

    @AfterReturning(value = "execution(* org.springframework.data.jpa.repository.JpaRepository+.delete(..)) and args(entity)")
    public void afterDelete(JoinPoint joinPoint, AuditEntity entity) {
	if (!isAuditing) {
	    try {
		isAuditing = true; // Set flag to indicate auditing is in progress
		entity.setOperation(AuditOperationsEnum.DELETE);
		callAuditWS(constructAuditDto(entity, entity.getOperation(), joinPoint.getSignature().getName()));
	    } catch (Exception e) {
		logger.error(e);
	    } finally {
		isAuditing = false; // Reset the flag after the call
	    }
	}
    }

    private void callAuditWS(AuditLogRequestDto dto) {
	auditService.insertAuditLog(dto);
    }

    private AuditLogRequestDto constructAuditDto(AuditEntity entity, AuditOperationsEnum operation, String methodName) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException {
	AuditLogRequestDto dto = new AuditLogRequestDto();
	dto.setEntityName(entity.getClass().getSimpleName());
	dto.setRowData(getRowData(entity));
	dto.setRowId(getRowId(entity) + "");
	dto.setUserId("1");
	dto.setMethodName(methodName);
	dto.setOperation(operation);
	return dto;
    }

    private String getRowData(AuditEntity entity) throws JsonProcessingException {
	ObjectMapper objectMapper = new ObjectMapper();
	return objectMapper.writeValueAsString(entity);
    }

    private Long getRowId(AuditEntity entity) throws IllegalArgumentException, IllegalAccessException {
	Long id = null;
	Field[] fields = entity.getClass().getDeclaredFields();
	for (Field field : fields) {
	    if (field.isAnnotationPresent(Id.class)) {
		field.setAccessible(true);
		id = (Long) field.get(entity);
		break;
	    }
	}
	return id;
    }
}
