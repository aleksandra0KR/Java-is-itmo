package ru.aleksandra0KR.bank.consoleInterface.Commands;


import ru.aleksandra0KR.bank.Entity.Bank.Bank;
import ru.aleksandra0KR.bank.Entity.Bank.CentralBank;
import ru.aleksandra0KR.bank.Entity.User.User;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

/**
 * Command class for adding an address to a user by its ID in the banking system.
 *
 * @author Aleksandra0KR
 * @version 1.0
 */

@CommandLine.Command(name = "-addAddress", description = "Add address to user")
public class AddAddressCommand implements Runnable {

  // Executes the command to add an address to a user.
  @Override
  public void run() {
    Scanner in = new Scanner(System.in);
    CentralBank centralBank = CentralBank.getInstance();

    System.out.println("Please, enter bank name:");
    String bankName = in.nextLine();

    Bank bank = centralBank.GetBank(bankName);
    if (bank == null) {
      System.out.println("No such bank");
      return;
    }

    System.out.println("Please, enter User's ID");
    UUID userId = UUID.fromString(in.nextLine());
    User user = bank.GetUser(userId);
    if (user == null) {
      System.out.println(
          "User is not client of a bank. You can register by running command -crateUser");
      return;
    }

    System.out.println("Please, enter address: ");
    String address = in.nextLine();
    user.setAddress(address);
  }
}
