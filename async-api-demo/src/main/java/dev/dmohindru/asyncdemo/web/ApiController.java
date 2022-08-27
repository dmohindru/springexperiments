package dev.dmohindru.asyncdemo.web;

import dev.dmohindru.asyncdemo.dto.ComputationResponse;
import dev.dmohindru.asyncdemo.service.ComputationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ApiController {

    final private ComputationService computationService;

    @GetMapping("sync")
    public ResponseEntity<ComputationResponse> getSyncResult() {

        return new ResponseEntity<>(computationService.syncComputation(), HttpStatus.OK);
    }

    @GetMapping("async")
    public ResponseEntity<ComputationResponse> getAsyncResult() {
        return new ResponseEntity<>(computationService.asyncComputation(), HttpStatus.OK);
    }
}
