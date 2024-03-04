package aleksandra0KR.Picocli;

import aleksandra0KR.Entity.Bank.Bank;
import aleksandra0KR.Entity.Bank.CentralBank;
import aleksandra0KR.Model.Account.Account;
import aleksandra0KR.Model.Transaction.Transaction;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

@CommandLine.Command(name = "-showHistoryOfTransactions", description = "Shows account's transactions by it's ID")
public class ShowHistoryOfTransactions implements Runnable{
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

        System.out.println("Account's history:");
        for (Transaction transaction: account.HistoryOfTransactions){
            transaction.printInfo();
        }
    }
}
