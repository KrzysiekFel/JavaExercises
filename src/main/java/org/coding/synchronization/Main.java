package org.coding.synchronization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numberOfOperations = 1000;
        int numberOfThreads = 10;
        int balanceStartValue = 100;
        AtomicInteger atomicStartValue = new AtomicInteger(balanceStartValue);


        // NO SYNCHRO
        BankAccount bankAccountNoSynchro = new BankAccount(balanceStartValue);
        List<Thread> threadsNoSynchro = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < numberOfOperations; j++) {
                    bankAccountNoSynchro.depositNoSynchro(10);
                    bankAccountNoSynchro.withdrawNoSynchro(10);
                }
            });
            threadsNoSynchro.add(thread);
            thread.start();
        }


        // SYNCHRO
        BankAccount bankAccountSynchro = new BankAccount(balanceStartValue);
        List<Thread> threadsSynchro = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < numberOfOperations; j++) {
                    bankAccountSynchro.depositSynchro(10);
                    bankAccountSynchro.withdrawSynchro(10);
                }
            });
            threadsSynchro.add(thread);
            thread.start();
        }


        // ATOMIC INTEGER
        BankAccount bankAccountAtomic = new BankAccount(atomicStartValue);
        List<Thread> threadsAtomic = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < numberOfOperations; j++) {
                    bankAccountAtomic.atomicDeposit(10);
                    bankAccountAtomic.atomicWithdraw(10);
                }
            });
            threadsAtomic.add(thread);
            thread.start();
        }


        for (Thread thread : threadsNoSynchro) {
            thread.join();
        }
        for (Thread thread : threadsSynchro) {
            thread.join();
        }
        for (Thread thread : threadsAtomic) {
            thread.join();
        }


        System.out.println("============================================");
        System.out.println("Start balance value: " + balanceStartValue + " PLN");
        System.out.println("Number of threads: " + numberOfThreads);
        System.out.println("Number of operations for each thread: " + numberOfOperations);
        System.out.println("============================================");
        System.out.println("|NO SYNCHRO| Final balance: " + bankAccountNoSynchro.getBalance());
        System.out.println("|SYNCHRO|    Final balance: " + bankAccountSynchro.getBalance());
        System.out.println("|ATOMIC INT| Final balance: " + bankAccountAtomic.getAtomicBalance());


        // CONCURRENT MAP
        Random random = new Random();
        List<Thread> threadsFromConcurrentMap = new ArrayList<>();
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                        'L', 'M', 'N', 'O', 'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z'};
        Map<Character, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        for (char letter : letters) {
            concurrentHashMap.put(letter, 0);
        }

        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < numberOfOperations; j++) {
                    int randomIndex = random.nextInt(letters.length);
                    char randomLetter = letters[randomIndex];
                    concurrentHashMap.merge(randomLetter, 1, Integer::sum);
                }
            });
            threadsFromConcurrentMap.add(thread);
            thread.start();
        }

        // MAP without synchronization
        List<Thread> threadsFromMap = new ArrayList<>();
        Map<Character, Integer> hashMap = new HashMap<>();
        for (char letter : letters) {
            hashMap.put(letter, 0);
        }

        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < numberOfOperations; j++) {
                    int randomIndex = random.nextInt(letters.length);
                    char randomLetter = letters[randomIndex];
                    hashMap.put(randomLetter, hashMap.get(randomLetter) + 1);
                }
            });
            threadsFromMap.add(thread);
            thread.start();
        }

        for (Thread thread : threadsFromConcurrentMap) {
            thread.join();
        }
        for (Thread thread : threadsFromMap) {
            thread.join();
        }

        int targetSum = numberOfThreads * numberOfOperations;
        int concurrentSum = 0;
        int noConcurrentSum = 0;

        for (int value : concurrentHashMap.values()) {
            concurrentSum += value;
        }

        for (int value : hashMap.values()) {
            noConcurrentSum += value;
        }
        System.out.println("============================================");
        System.out.println("ConcurrentHashMap VS HashMap without synchro");
        System.out.println("Targeted value should be:      " + targetSum);
        System.out.println("Value for concurrent hash map: " + concurrentSum);
        System.out.println("Value for no synchro hash map: " + noConcurrentSum);

    }
}
