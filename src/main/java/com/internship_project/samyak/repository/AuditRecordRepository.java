package com.internship_project.samyak.repository;

import com.internship_project.samyak.entity.AuditRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRecordRepository extends JpaRepository<AuditRecord, Long> {
    // Additional query methods if needed
}