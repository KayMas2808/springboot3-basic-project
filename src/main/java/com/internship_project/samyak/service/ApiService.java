//main logic of application
package com.internship_project.samyak.service;

import com.internship_project.samyak.entity.AuditRecord;
import com.internship_project.samyak.repository.AuditRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.concurrent.CompletableFuture;

@Service
public class ApiService {

    private final RestTemplate restTemplate;
    private final AuditRecordRepository auditRecordRepository;

    @Autowired
    public ApiService(AuditRecordRepository auditRecordRepository) {
        this.restTemplate = new RestTemplate();
        this.auditRecordRepository = auditRecordRepository;
    }

    //async allows to run in background
    @Async
    public CompletableFuture<Void> callExternalApiAndAudit(String requestId, String apiUrl) {
        //receives request to make external api call
        AuditRecord auditRecord = new AuditRecord();
        auditRecord.setRequestId(requestId);
        auditRecord.setTimestamp(new Timestamp(System.currentTimeMillis()));

        long startTime = System.currentTimeMillis();

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            //sends request
            auditRecord.setStatusCode(response.getStatusCodeValue());
        } catch (HttpStatusCodeException e) {
            auditRecord.setStatusCode(e.getStatusCode().value());
            auditRecord.setErrorMessage(e.getMessage());
        } finally {
            auditRecord.setResponseTimeMs(System.currentTimeMillis() - startTime);
            auditRecordRepository.save(auditRecord);
            //audit logging
        }

        return CompletableFuture.completedFuture(null);
    }
}