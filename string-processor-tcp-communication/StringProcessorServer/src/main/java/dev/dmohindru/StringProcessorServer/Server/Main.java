package dev.dmohindru.StringProcessorServer.Server;

import dev.dmohindru.StringProcessorServer.processor.CLIProcessor;
import dev.dmohindru.StringProcessorServer.processor.HttpConnectionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Component
@Slf4j
public class Main implements CommandLineRunner {
    private CLIProcessor cliProcessor = new CLIProcessor();

    private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    @Override
    public void run(String... args) throws Exception {
        Integer serverPort = cliProcessor.getServerPort(args);
        log.info("String Processor running on port {}", serverPort);
        try(ServerSocket serverSocket = new ServerSocket(serverPort)) {
            while (true) {
                Socket incomingConnection = serverSocket.accept();
                executor.submit(new HttpConnectionHandler(incomingConnection));
            }
        }
    }
}
