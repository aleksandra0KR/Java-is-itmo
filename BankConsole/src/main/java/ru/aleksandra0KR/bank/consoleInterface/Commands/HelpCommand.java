package ru.aleksandra0KR.bank.consoleInterface.Commands;

import picocli.CommandLine;

/**
 * Command class for output all existing commands
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-help", description = "Prints all commands")
public class HelpCommand implements Runnable {

    // Prints all commands
    @Override
    public void run() {
        System.out.println("Commands:");
        System.out.println("-createBank");
        System.out.println("-createUser");
        System.out.println("-createAccount");
        System.out.println("-addAddress");
        System.out.println("-addPassport");
        System.out.println("-transaction");
        System.out.println("-cancelOperation");
        System.out.println("-showBalance");
        System.out.println("-showHistoryOfTransactions");
        System.out.println("-addDays");
        System.out.println("-changeCreditCommission");
        System.out.println("-changeCreditLimit");
        System.out.println("-changeDebitPercentage");
        System.out.println("-changeDepositPercentage");
        System.out.println("-changeUntrustedLimit");
        System.out.println("-notifyUsers");
        System.out.println("-showHistoryOfTransactions");
        System.out.println("-showNotifications");
        System.out.println("-subscribeAccountOnNotifications");
        System.out.println("-unSubscribeAccountFROMNotifications");
        System.out.println("-stop");
    }
}