package aleksandra0KRBank.Console.Interface.Commands;

import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Entity.Bank.CentralBank;
import picocli.CommandLine;

import java.util.Scanner;


/**
 * Command class for notifying all users of a bank
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-notifyUsers", description = "Sends to users notifications from bank")

public class NotifyUsersCommand implements Runnable{

    // Sends to users notifications from bank
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        CentralBank centralBank = CentralBank.getInstance();

        System.out.println("Please, enter bank name: ");
        String bankName = in.nextLine();

        Bank bank = centralBank.GetBank(bankName);
        if(bank == null) {
            System.out.println("No such bank");
            return;
        }
        System.out.println("Please, enter your message: ");
        String message = in.nextLine();
        bank.Notify(message);

    }
}
