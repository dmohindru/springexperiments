package dev.dmohindru.asyncdemo.service;

import dev.dmohindru.asyncdemo.dto.ComputationResponse;

public interface ComputationService {

    ComputationResponse syncComputation();
    ComputationResponse asyncComputation();
}
