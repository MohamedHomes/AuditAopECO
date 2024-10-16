package com.audit.models.orm;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.audit.aop.AuditEntity;
import com.audit.enums.AuditOperationsEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ECO_AUDIT_LOGS")
public class AuditLog extends AuditEntity {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "ECO_AUDIT_SEQ",
	    sequenceName = "ECO_AUDIT_SEQ",
	    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
	    generator = "ECO_AUDIT_SEQ")
    private Long id;

    @Basic
    @Column(name = "LOG_TABLE")
    private String logTable;

    @Basic
    @Column(name = "OPERATION_TYPE")
    @Enumerated(EnumType.ORDINAL)
    private AuditOperationsEnum operationType;

    @Basic
    @Column(name = "LOG_ROW_ID")
    private String logRowId;

    @Basic
    @Column(name = "LOG_ROW_DATA_AFTER")
    private String logRowDataAfter;

    @Basic
    @Column(name = "USER_ID")
    private String userId;

    @Basic
    @Column(name = "OPERATION_DATE")
    private Date operationDate;

    @Basic
    @Column(name = "METHOD_NAME")
    private String methodName;

}
