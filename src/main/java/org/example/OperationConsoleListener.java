package org.example;

import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OperationConsoleListener {

    private final Scanner scanner;
    private final Map<ConsoleOperationType, OperationCommandProcessor> processorMap;

    public OperationConsoleListener(
            Scanner scanner,
            List<OperationCommandProcessor> processorList) {
        this.scanner = scanner;
        this.processorMap = processorList.stream().collect(
                Collectors.toMap(
                        OperationCommandProcessor::getOperationType,
                        processor -> processor
                )
        );
    }

    public void listenUpdates() {
        while (!Thread.currentThread().isInterrupted()) {
            processNextOperation(listenNextOperation());
        }
    }

    private ConsoleOperationType listenNextOperation() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("\nPlease enter the next operation: ");
            printAllOperations();
            try {
                return ConsoleOperationType.valueOf(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid next operation");
            }
        }
        return null;
    }

    private void processNextOperation(ConsoleOperationType operationType) {
        try {
            OperationCommandProcessor processor = processorMap.get(operationType);
            processor.processOperation();
        } catch (Exception e) {
            System.out.println("Operation " /*+ operationType*/ + "failed with message " + e.getMessage());
        }
    }

    private void printAllOperations() {
        for (ConsoleOperationType operationType : processorMap.keySet()) {
            System.out.println(operationType.toString());
        }
    }

    public void start() {
        System.out.println("Starting operation console listener");
    }

    public void stop() {
        System.out.println("Stopping operation console listener");
    }

}