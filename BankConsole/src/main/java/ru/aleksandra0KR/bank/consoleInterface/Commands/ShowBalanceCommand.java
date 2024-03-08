package ru.aleksandra0KR.bank.consoleInterface.Commands;

import ru.aleksandra0KR.bank.Entity.Bank.Bank;
import ru.aleksandra0KR.bank.Entity.Bank.CentralBank;
import ru.aleksandra0KR.bank.Model.Account.Account;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

/**
 * Command class for showing balance of specific account by its ID
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-showBalance", description = "Shows account balance by it's ID")
public class ShowBalanceCommand implements Runnable{

    // Shows account balance by its ID
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

        System.out.println("Please, enter account ID: ");
        UUID accountId = UUID.fromString(in.nextLine());

        Account account = bank.GetAccount(accountId);

        if(account == null){
            System.out.println("No such Account in a bank. You can register by running command -createAccount");
            return;
        }

        System.out.println("Current balance: " + account.getMoney());

    }
}
