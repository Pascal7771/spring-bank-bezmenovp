package org.example.account;

import org.example.user.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    private final Map<Integer, Account> accauntsMap;
    private int idCounter;
    private final AccountProperties accountProperties;

    public AccountService(AccountProperties accountProperties) {
        this.accauntsMap = new HashMap<>();
        this.idCounter = 0;
        this.accountProperties = accountProperties;
    }

    public Account createAccount(User user) {
        idCounter++;
        Account account = new Account(idCounter, user.getId(), accountProperties.getDefaultAccountAmount());
        accauntsMap.put(account.getId(), account);
        return account;
    }

    public Optional<Account> findAccountById(int id) {
        return Optional.ofNullable(accauntsMap.get(id));
    }

    public List<Account> getAllUserAccounts(int userId) {
        return accauntsMap.values().stream()
                .filter(account -> account.getUserId() == userId)
                .toList();
    }

    public void depositAccount(int accountId, int moneyToDeposit) {
        Account account = findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account with id " + accountId + " not found"));
        if (moneyToDeposit <= 0) {
            throw new IllegalArgumentException("Account replenishment amount " + moneyToDeposit + " cannot be <= 0");
        }
        account.setMoneyAmount(account.getMoneyAmount() + moneyToDeposit);
    }

    public void withdrawAccount(int accountId, int moneyToWithdraw) {
        Account account = findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account with id " + accountId + " not found"));
        if (moneyToWithdraw <= 0) {
            throw new IllegalArgumentException("Amount debited from the account " + moneyToWithdraw + " cannot be <= 0");
        }
        if (account.getMoneyAmount() < moneyToWithdraw) {
            throw new IllegalArgumentException("Amount debited from the account " + moneyToWithdraw + " cannot exceed the balance " + account.getMoneyAmount());
        }
        account.setMoneyAmount(account.getMoneyAmount() - moneyToWithdraw);
    }

    public Account closeAccount(int accountId) {
        Account account = findAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account with id " + accountId + " not found"));
        List<Account> accountList = getAllUserAccounts(account.getUserId());
        if (accountList.size() == 1) {
            throw new IllegalArgumentException("You can't close a single account");
        }
        Account accountToDeposit = accountList.stream().filter(it -> it.getId() != accountId).findFirst().orElseThrow();
        accountToDeposit.setMoneyAmount(accountToDeposit.getMoneyAmount() + account.getMoneyAmount());
        accauntsMap.remove(account.getId());
        return account;
    }

    public void transferMoney(int fromAccountId, int toAccountId, int amount) {
        Account sourceAccount = findAccountById(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Account with id " + fromAccountId + " not found"));
        Account targetAccount = findAccountById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Account with id " + toAccountId + " not found"));
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount debited from the account " + amount + " cannot be <= 0");
        }
        if (sourceAccount.getMoneyAmount() < amount) {
            throw new IllegalArgumentException("Amount debited from the account " + amount + " cannot exceed the balance " + sourceAccount.getMoneyAmount());
        }

        int totalAmountToDeposit = sourceAccount.getUserId() != targetAccount.getUserId()
                ? (int) (amount - amount * accountProperties.getTransferCommission())
                : amount;

        sourceAccount.setMoneyAmount(sourceAccount.getMoneyAmount() - amount);
        targetAccount.setMoneyAmount(targetAccount.getMoneyAmount() + totalAmountToDeposit);
        System.out.println("Successfully transferred " + amount + " from " + sourceAccount.getUserId() + " to " + targetAccount.getUserId());
    }

}