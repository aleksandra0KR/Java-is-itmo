package aleksandra0KRBank.Interface.Commands;

import aleksandra0KRBank.Entity.Bank.Bank;
import aleksandra0KRBank.Entity.Bank.CentralBank;
import picocli.CommandLine;

import java.math.BigDecimal;
import java.util.Scanner;

@CommandLine.Command(name = "-changeCreditLimit", description = "Changes credit limit of a bank")
public class ChangeCreditLimitCommand implements Runnable {
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

        System.out.println("Please, enter new credit limit: ");
        BigDecimal limit = in.nextBigDecimal();
        bank.setCreditLimit(limit);
    }

}
