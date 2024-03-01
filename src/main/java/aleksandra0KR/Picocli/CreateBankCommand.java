package aleksandra0KR.Picocli;

import aleksandra0KR.Entity.Account.BankAccount;
import aleksandra0KR.Entity.Bank.Bank;
import aleksandra0KR.Entity.Bank.BankBuilder;
import aleksandra0KR.Entity.Bank.CentralBank;
import aleksandra0KR.Tools.DepositMoneyGapPercentage;
import picocli.CommandLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@CommandLine.Command(name = "-createBank", description = "Creates new bank")
public class CreateBankCommand implements Runnable{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        var centralBank = CentralBank.getInstance();

        System.out.println("Please, enter bank name:");
        String bankName = in.nextLine();

        var bank = centralBank.GetBank(bankName);
        if(bank != null) {
            System.out.println("Such bank already exists");
            return;
        }

        var bankBuilder = new BankBuilder();
        bankBuilder.SetBankName(bankName);
        System.out.println("Please, enter Deposit Percentage (LowerBoundary, TopBoundary, Percentage):");
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

        bankBuilder.SetDepositPercentage(depositPercentage);

        System.out.println("Please, enter Credit Limit:");
        BigDecimal creditLimit = new BigDecimal(in.nextLine());
        bankBuilder.SetCreditLimit(creditLimit);

        System.out.println("Please, enter Credit Commission:");
        BigDecimal creditCommission = new BigDecimal(in.nextLine());
        bankBuilder.SetCreditCommission(creditCommission);

        System.out.println("Please, enter Debit Percentage:");
        BigDecimal debitPercentage = new BigDecimal(in.nextLine());
        bankBuilder.SetDebitPercentage(debitPercentage);

        System.out.println("Please, enter Money:");
        BigDecimal money = new BigDecimal(in.nextLine());
        bankBuilder.SetBankAccount(new BankAccount(money, centralBank.getBankCalendar()));


        System.out.println("Please, enter untrusted users limit:");
        BigDecimal untrustedLimit = new BigDecimal(in.nextLine());
        bankBuilder.SetUntrustedLimit(untrustedLimit);

        centralBank.AddBank(bankBuilder.GetBank());

        System.out.println("Bank " + bankName+ " is created");

    }
}
