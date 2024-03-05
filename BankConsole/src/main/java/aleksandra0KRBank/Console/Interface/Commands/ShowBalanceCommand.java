package aleksandra0KRBank.Console.Interface.Commands;

import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Entity.Bank.CentralBank;
import aleksandra0KRBank.Model.Account.Account;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

@CommandLine.Command(name = "-showBalance", description = "Shows account balance by it's ID")
public class ShowBalanceCommand implements Runnable{
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
