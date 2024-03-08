package ru.aleksandra0KR.bank.consoleInterface.Commands;

import ru.aleksandra0KR.bank.Entity.Bank.Bank;
import ru.aleksandra0KR.bank.Entity.Bank.CentralBank;
import ru.aleksandra0KR.bank.Entity.User.User;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

/**
 * Command class for unsubscribe users from notifications of a bank by name of a bank and users ID
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-unSubscribeAccountFROMNotifications", description = "Unsubscribes user from bank's notifications by it's id")

public class UnSubscribeAccountFromNotificationsCommand implements Runnable{

    // Unsubscribes user from bank's notifications by it's id
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
        bank.Detach(user);
    }
}
