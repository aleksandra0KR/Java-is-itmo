package aleksandra0KR.Picocli;

import aleksandra0KR.Entity.Bank.CentralBank;
import picocli.CommandLine;

import java.util.Scanner;

@CommandLine.Command(name = "-addDays", description = "Adds days to central bank")
public class AddDaysCommand implements Runnable{
    @Override
    public void run() {

        Scanner in = new Scanner(System.in);
        CentralBank centralBank = CentralBank.getInstance();

        System.out.println("Please, enter amount of days:");
        int days = in.nextInt();
        centralBank.AddTime(days);

    }
}
