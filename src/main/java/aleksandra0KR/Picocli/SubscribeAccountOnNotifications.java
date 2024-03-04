package aleksandra0KR.Picocli;

import aleksandra0KR.Entity.Bank.Bank;
import aleksandra0KR.Entity.Bank.CentralBank;
import aleksandra0KR.Entity.User.User;
import aleksandra0KR.Model.Account.Account;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

@CommandLine.Command(name = "-subscribeAccountOnNotifications", description = "Subscribes user on bank's notifications by it's id")
public class SubscribeAccountOnNotifications implements Runnable {

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
        System.out.println("Please, enter user ID: ");
        UUID userId = UUID.fromString(in.nextLine());

        User user = bank.GetUser(userId);

        if(user == null){
            System.out.println("No such User in a bank. You can register by running command -createUser");
            return;
        }
        bank.Attach(user);
    }
}
