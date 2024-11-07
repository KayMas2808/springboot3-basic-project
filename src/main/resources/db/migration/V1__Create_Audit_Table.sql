CREATE TABLE audit_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    request_id VARCHAR(50) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status_code INT,
    response_time_ms BIGINT,
    error_message VARCHAR(255)
);

CREATE INDEX idx_request_id ON audit_records (request_id);
