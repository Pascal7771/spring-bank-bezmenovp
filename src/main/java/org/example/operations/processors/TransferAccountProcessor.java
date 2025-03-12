package org.example.operations.processors;

import org.example.account.AccountService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class TransferAccountProcessor implements OperationCommandProcessor {

    private final Scanner scanner;
    private final AccountService accountService;

    public TransferAccountProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter source account Id: ");
        int sourceAccountId = scanner.nextInt();
        System.out.println("Enter target account Id: ");
        int targetAccountId = scanner.nextInt();
        System.out.println("Enter amount to transfer: ");
        int transferAmount = scanner.nextInt();
        accountService.transferMoney(sourceAccountId, targetAccountId, transferAmount);
        System.out.println("Transfer successful!");
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_TRANSFER;
    }

}