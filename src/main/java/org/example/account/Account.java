package org.example.account;

public class Account {

    private final int id;
    private final int UserId;
    private int moneyAmount;

    public Account(int id, int userId, int userAmount) {
        this.id = id;
        UserId = userId;
        moneyAmount = userAmount;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return UserId;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", UserId=" + UserId +
                ", moneyAmount=" + moneyAmount +
                '}';
    }

}