package aleksandra0KRBank.Console.Interface.Commands;

import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Entity.Bank.CentralBank;
import aleksandra0KRBank.Entity.User.User;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

/**
 * Command class for showing notifications of specific user by its ID
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-showNotifications", description = "Shows user's messages from bank")

public class ShowNotificationsCommand implements Runnable{

    // Shows user's messages from bank by ID
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

        for(String message: user.get_messages()){
            System.out.println(message);
        }
    }
}
