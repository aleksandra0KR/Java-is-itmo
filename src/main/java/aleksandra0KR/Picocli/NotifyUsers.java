package aleksandra0KR.Picocli;

import aleksandra0KR.Entity.Bank.Bank;
import aleksandra0KR.Entity.Bank.CentralBank;
import aleksandra0KR.Entity.User.User;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

@CommandLine.Command(name = "-notifyUsers", description = "Sends to users notifications from bank")

public class NotifyUsers implements Runnable{
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
