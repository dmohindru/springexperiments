package dev.dmohindru.asyncdemo.service;

import dev.dmohindru.asyncdemo.dto.ComputationResponse;
import dev.dmohindru.asyncdemo.util.Util;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ComputationServiceImpl implements ComputationService {
    @Override
    public ComputationResponse syncComputation() {
        ComputationResponse computationResponse = new ComputationResponse();
        computationResponse.setComputationName("Sync computation result");
        // Record start time
        long startTime = System.currentTimeMillis();
        // Start first computation
        computationResponse.setResultOne(
                Util.getResult("First result", 2000, 1));
        // Start second computation
        computationResponse.setResultTwo(
                Util.getResult("Second result", 3000, 2));
        // Start third computation
        computationResponse.setResultThree(
                Util.getResult("Third result", 4000, 3));
        // Start fourth computation
        computationResponse.setResultFour(
                Util.getResult("Fourth result", 5000, 4));
        long endTime = System.currentTimeMillis();
        computationResponse.setTotalComputationTime(endTime - startTime);

        return computationResponse;
    }

    @Override
    public ComputationResponse asyncComputation() {
        ComputationResponse computationResponse = new ComputationResponse();
        computationResponse.setComputationName("Async computation result");
        long startTime = System.currentTimeMillis();
        // Start all the computation in parallel
        CompletableFuture.allOf(
                Util.fromSupplier("First Async computation", 2000, 1)
                        .thenAccept(computationResponse::setResultOne),
                Util.fromSupplier("Second Async computation", 3000, 2)
                        .thenAccept(computationResponse::setResultTwo),
                Util.fromSupplier("Third Async computation", 4000, 3)
                        .thenAccept(computationResponse::setResultThree),
                Util.fromSupplier("Fourth Async computation", 5000, 4)
                        .thenAccept(computationResponse::setResultFour)
                ).join();
        long endTime = System.currentTimeMillis();
        computationResponse.setTotalComputationTime(endTime - startTime);

        return computationResponse;
    }
}
