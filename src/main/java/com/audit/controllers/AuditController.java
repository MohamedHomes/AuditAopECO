package com.audit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.audit.exceptions.BusinessException;
import com.audit.models.dto.AuditLogRequestDto;
import com.audit.services.AuditService;

@RestController
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @PostMapping("/insert")
    public ResponseEntity<Object> insert(@RequestBody AuditLogRequestDto auditLogRequestDto) throws BusinessException {
	System.out.println("homes");
	return new ResponseEntity<>(auditService.insertAuditLog(auditLogRequestDto), HttpStatus.OK);
    }

}
