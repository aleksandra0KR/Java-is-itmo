package aleksandra0KR.Picocli;

import aleksandra0KR.Entity.Bank.CentralBank;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

@CommandLine.Command(name = "-addPassport", description = "Add passport to user")
public class AddPassportCommand implements Runnable{

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        var centralBank = CentralBank.getInstance();

        System.out.println("Please, enter bank name:");
        String bankName = in.nextLine();

        var bank = centralBank.GetBank(bankName);
        if(bank == null) {
            System.out.println("No such bank");
            return;
        }

        System.out.println("Please, enter User's ID");
        UUID userId = UUID.fromString(in.nextLine());
        var user = bank.GetUser(userId);
        if(user == null){
            System.out.println("User is not client of a bank. You can register by running command -crateUser");
            return;
        }

        System.out.println("Please, enter passport: ");
        String passport = in.nextLine();

        user.SetPassport(passport);
    }
}
