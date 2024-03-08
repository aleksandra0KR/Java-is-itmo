package ru.aleksandra0KR.bank.consoleInterface.Commands;

import ru.aleksandra0KR.bank.consoleInterface.AccountHandlers.AccountHandler;
import ru.aleksandra0KR.bank.consoleInterface.AccountHandlers.AccountHandlerBuilder;
import ru.aleksandra0KR.bank.Entity.Bank.CentralBank;
import ru.aleksandra0KR.bank.Entity.User.User;
import picocli.CommandLine;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

/**
 * Command class for creating an account for a certain user in a certain bank
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-createAccount", description = "Creates new account")
public class CreateAccountCommand implements Runnable {

    // Creates new account in needed bank
    @Override
    public void run() {

        Scanner in = new Scanner(System.in);
        var centralBank = CentralBank.getInstance();

        System.out.println("Please, enter bank name:");
        String bankName = in.nextLine();

        var bank = centralBank.GetBank(bankName);
        if(bank == null) {
            System.out.println("No such bank");
            return;
        }

        System.out.println("Please, enter User's ID");
        UUID userId = UUID.fromString(in.nextLine());
        User user = bank.GetUser(userId);
        if(user == null){
            System.out.println("User is not client of a bank. You can register by running command -crateUser");
            return;
        }

        System.out.println("Please, enter type of account: ");
        System.out.println("debit");
        System.out.println("credit");
        System.out.println("deposit");
        String typeOfAccount = in.nextLine();

        System.out.println("Please, enter Money:");
        BigDecimal money = new BigDecimal(in.nextLine());

        System.out.println("Please, enter years");
        int years = in.nextInt();

        AccountHandlerBuilder handlerBuilder = new AccountHandlerBuilder();
        AccountHandler handler = handlerBuilder.GetHandler();
        handler.HandleRequest(user, bank, typeOfAccount, money, years);

    }
}
