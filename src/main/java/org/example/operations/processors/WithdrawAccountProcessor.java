package org.example.operations.processors;

import org.example.account.AccountService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class WithdrawAccountProcessor implements OperationCommandProcessor {

    private final Scanner scanner;
    private final AccountService accountService;

    public WithdrawAccountProcessor(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    @Override
    public void processOperation() {
        System.out.println("Enter account Id: ");
        int accountId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter amount to withdraw: ");
        int amount = Integer.parseInt(scanner.nextLine());
        accountService.withdrawAccount(accountId, amount);
        System.out.println("Account with ID " + accountId + " successfully withdrawn " + amount);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_WITHDRAW;
    }

}