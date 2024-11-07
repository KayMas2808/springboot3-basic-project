//data transfer object(dto)
//represents data sent by client when calling api
package com.internship_project.samyak.dto;

import jakarta.validation.constraints.NotBlank;


public class RequestDto {

    @NotBlank(message = "Request ID is mandatory")
    private String requestId;

    @NotBlank(message = "API URL cannot be empty")
    private String apiUrl;
    //validates that requestId and apiUrl arent empty or missing.

    // Getters and Setters
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
