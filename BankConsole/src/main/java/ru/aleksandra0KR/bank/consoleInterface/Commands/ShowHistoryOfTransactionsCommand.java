package ru.aleksandra0KR.bank.consoleInterface.Commands;

import ru.aleksandra0KR.bank.Entity.Bank.Bank;
import ru.aleksandra0KR.bank.Entity.Bank.CentralBank;
import ru.aleksandra0KR.bank.Model.Account.Account;
import ru.aleksandra0KR.bank.Model.Transaction.Transaction;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

/**
 * Command class for showing history of a transactions of a specific account by its ID
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-showHistoryOfTransactions", description = "Shows account's transactions by it's ID")
public class ShowHistoryOfTransactionsCommand implements Runnable {

  // Shows account's transactions by its ID
  @Override
  public void run() {
    Scanner in = new Scanner(System.in);
    CentralBank centralBank = CentralBank.getInstance();

    System.out.println("Please, enter bank name: ");
    String bankName = in.nextLine();

    Bank bank = centralBank.GetBank(bankName);
    if (bank == null) {
      System.out.println("No such bank");
      return;
    }

    System.out.println("Please, enter account ID: ");
    UUID accountId = UUID.fromString(in.nextLine());

    Account account = bank.GetAccount(accountId);

    if (account == null) {
      System.out.println(
          "No such Account in a bank. You can register by running command -createAccount");
      return;
    }

    System.out.println("Account's history:");
    for (Transaction transaction : account.getHistoryOfTransactions()) {
      transaction.printInfo();
    }
  }
}
