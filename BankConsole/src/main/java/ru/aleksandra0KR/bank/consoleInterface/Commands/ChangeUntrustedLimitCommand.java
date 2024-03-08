package ru.aleksandra0KR.bank.consoleInterface.Commands;

import ru.aleksandra0KR.bank.Entity.Bank.Bank;
import ru.aleksandra0KR.bank.Entity.Bank.CentralBank;
import picocli.CommandLine;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Command class for changing untrusted limit of a certain bank by its name
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-changeUntrustedLimit", description = "Changes untrusted limit of a bank")
public class ChangeUntrustedLimitCommand implements Runnable {

    // Changes untrusted limit of a bank
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

        System.out.println("Please, enter new untrusted limit: ");
        BigDecimal percentage = in.nextBigDecimal();
        bank.setUntrustedLimit(percentage);
    }
}
