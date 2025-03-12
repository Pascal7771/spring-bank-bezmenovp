package org.example.user;

import org.example.account.Account;
import java.util.List;

public class User {

    private final int id;
    private final String name;
    private final List<Account> accountList;

    public User(int id, String name, List<Account> accountList) {
        this.id = id;
        this.name = name;
        this.accountList = accountList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountList=" + accountList +
                '}';
    }

}