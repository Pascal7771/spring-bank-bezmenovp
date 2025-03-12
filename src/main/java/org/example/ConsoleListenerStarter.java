package org.example;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.io.Console;

@Component
public class ConsoleListenerStarter {

    private final OperationConsoleListener consoleListener;
    private static Thread consoleListenerThread;

    public ConsoleListenerStarter(OperationConsoleListener consoleListener) {
        this.consoleListener = consoleListener;
    }

    @PostConstruct
    public void postConstruct() {
        consoleListenerThread = new Thread(() -> {
            consoleListener.start();
            consoleListener.listenUpdates();
        });
        consoleListenerThread.start();
    }

    @PreDestroy
    public void preDestroy() {
        consoleListenerThread.interrupt();
        consoleListener.stop();
    }

}