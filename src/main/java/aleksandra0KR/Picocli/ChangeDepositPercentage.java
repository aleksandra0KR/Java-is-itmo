package aleksandra0KR.Picocli;


import aleksandra0KR.Entity.Bank.Bank;
import aleksandra0KR.Entity.Bank.CentralBank;
import aleksandra0KR.Tools.DepositMoneyGapPercentage;
import picocli.CommandLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

@CommandLine.Command(name = "-changeDepositPercentage", description = "Changes deposit percentage of a bank")
public class ChangeDepositPercentage implements Runnable {
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

        System.out.println("Please, enter new deposit percentage (LowerBoundary, TopBoundary, Percentage):");
        String numbers = in.nextLine();
        String[] numberArray = numbers.split(" ");

        var depositPercentage = new ArrayList<DepositMoneyGapPercentage>();
        if(numberArray.length % 3 != 0) {
            System.out.println("Error format input");
            return;
        }

        for (int i = 0; i < numberArray.length; i++) {
            BigDecimal lowerBoundary = new BigDecimal(numberArray[i]);
            BigDecimal topBoundary = new BigDecimal(numberArray[++i]);
            BigDecimal percentage = new BigDecimal(numberArray[++i]);
            var percentageGap = new DepositMoneyGapPercentage(lowerBoundary, topBoundary, percentage);
            depositPercentage.add(percentageGap);
        }

        bank.setDepositPercentage(depositPercentage);
    }
}
