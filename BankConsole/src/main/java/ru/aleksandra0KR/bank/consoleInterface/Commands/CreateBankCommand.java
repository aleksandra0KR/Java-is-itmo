package ru.aleksandra0KR.bank.consoleInterface.Commands;

import ru.aleksandra0KR.bank.Entity.Account.BankAccount;
import ru.aleksandra0KR.bank.Entity.Bank.Bank;
import ru.aleksandra0KR.bank.Entity.Bank.BankBuilder;
import ru.aleksandra0KR.bank.Entity.Bank.CentralBank;
import ru.aleksandra0KR.bank.Tools.DepositMoneyGapPercentage;
import picocli.CommandLine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Command class for creating a bank
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-createBank", description = "Creates new bank")
public class CreateBankCommand implements Runnable{

    // Creates new bank
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        CentralBank centralBank = CentralBank.getInstance();

        System.out.println("Please, enter bank name:");
        String bankName = in.nextLine();

        Bank bank = centralBank.GetBank(bankName);
        if(bank != null) {
            System.out.println("Such bank already exists");
            return;
        }

        BankBuilder bankBuilder = new BankBuilder();
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

        System.out.println("Please, enter Credit Commission:");
        BigDecimal creditCommission = new BigDecimal(in.nextLine());
        bankBuilder.SetCreditRules(creditLimit, creditCommission);

        System.out.println("Please, enter Debit Percentage:");
        BigDecimal debitPercentage = new BigDecimal(in.nextLine());
        bankBuilder.SetDebitPercentage(debitPercentage);

        System.out.println("Please, enter Money:");
        BigDecimal money = new BigDecimal(in.nextLine());
        bankBuilder.SetBankAccount(new BankAccount(money, centralBank.getBankCalendar()));


        System.out.println("Please, enter untrusted users limit:");
        BigDecimal untrustedLimit = new BigDecimal(in.nextLine());
        bankBuilder.SetUntrustedRules(untrustedLimit);

        centralBank.AddBank(bankBuilder.GetBank());

        System.out.println("Bank " + bankName+ " is created");

    }
}
