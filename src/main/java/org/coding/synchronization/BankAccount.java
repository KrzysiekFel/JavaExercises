package org.coding.synchronization;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {
    private int balance;
    private AtomicInteger atomicBalance;

    int getBalance() {
        return this.balance;
    }

    int getAtomicBalance() {
        return this.atomicBalance.get();
    }

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public BankAccount(AtomicInteger atomicBalance) {
        this.atomicBalance = atomicBalance;
    }

    public void depositNoSynchro(int money) {
        this.balance += money;
    }

    public void withdrawNoSynchro(int money) {
        this.balance -= money;
    }

    public synchronized void depositSynchro(int money) {
        this.balance += money;
    }

    public synchronized void withdrawSynchro(int money) {
        this.balance -= money;
    }

    public void atomicDeposit(int money) {
        this.atomicBalance.addAndGet(money);
    }

    public void atomicWithdraw(int money) {
        this.atomicBalance.addAndGet(-money);
    }
}
