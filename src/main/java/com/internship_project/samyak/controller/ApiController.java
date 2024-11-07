//listens for requests
package com.internship_project.samyak.controller;

import com.internship_project.samyak.dto.RequestDto;
import com.internship_project.samyak.service.ApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//tells spring that this class handles web requests
@RequestMapping("/api")
//sets base url for controller
public class ApiController {

    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/call")
    public ResponseEntity<String> callExternalApi(@Valid @RequestBody RequestDto requestDto) {
        //@Valid does automatic validation
        apiService.callExternalApiAndAudit(requestDto.getRequestId(), requestDto.getApiUrl());
        return ResponseEntity.accepted().body("Request accepted for processing.");
    }
    //mapped to POST call, takes json payload(RequestDto), @Valid ensures RequestDto has reqd. fields.
    //returns http response telling that request accepted.
}