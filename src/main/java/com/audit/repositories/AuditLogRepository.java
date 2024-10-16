package com.audit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.audit.models.orm.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

}
