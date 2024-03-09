package ru.aleksandra0KR.bank.consoleInterface.Commands;

import ru.aleksandra0KR.bank.Entity.Bank.Bank;
import ru.aleksandra0KR.bank.Entity.Bank.CentralBank;
import picocli.CommandLine;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Command class for changing commission of a certain bank by its name
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-changeCreditCommission", description = "Changes credit commission of a bank")
public class ChangeCreditCommissionCommand implements Runnable {

  // Changes credit commission of a bank
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

    System.out.println("Please, enter new credit commission: ");
    BigDecimal commission = in.nextBigDecimal();
    bank.setCreditCommission(commission);
  }
}
