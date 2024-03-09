package ru.aleksandra0KR.bank.consoleInterface.Commands;

import ru.aleksandra0KR.bank.Entity.Bank.CentralBank;
import picocli.CommandLine;

import java.util.Scanner;

/**
 * Command class for adding days to a central bank in the banking system.
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-addDays", description = "Adds days to central bank")
public class AddDaysCommand implements Runnable {

  // Executes the command to add days to a central bank.
  @Override
  public void run() {

    Scanner in = new Scanner(System.in);
    CentralBank centralBank = CentralBank.getInstance();

    System.out.println("Please, enter amount of days:");
    int days = in.nextInt();
    centralBank.AddTime(days);

  }
}
