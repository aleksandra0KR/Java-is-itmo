package aleksandra0KRBank.Console.Interface.Commands;

import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Entity.Bank.CentralBank;
import picocli.CommandLine;

import java.math.BigDecimal;
import java.util.Scanner;

@CommandLine.Command(name = "-changeDebitPercentage", description = "Changes debit percentage of a bank")

public class ChangeDebitPercentageCommand implements Runnable {
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

        System.out.println("Please, enter new debit percentage: ");
        BigDecimal percentage = in.nextBigDecimal();
        bank.setDebitPercentage(percentage);
    }
}
