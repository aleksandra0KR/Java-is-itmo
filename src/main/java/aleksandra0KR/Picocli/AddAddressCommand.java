package aleksandra0KR.Picocli;


import aleksandra0KR.Entity.Bank.Bank;
import aleksandra0KR.Entity.Bank.CentralBank;
import aleksandra0KR.Entity.User.User;
import picocli.CommandLine;

import java.util.Scanner;
import java.util.UUID;

@CommandLine.Command(name = "-addAddress", description = "Add address to user")
public class AddAddressCommand implements Runnable{
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        CentralBank centralBank = CentralBank.getInstance();

        System.out.println("Please, enter bank name:");
        String bankName = in.nextLine();

        Bank bank = centralBank.GetBank(bankName);
        if(bank == null) {
            System.out.println("No such bank");
            return;
        }

        System.out.println("Please, enter User's ID");
        UUID userId = UUID.fromString(in.nextLine());
        User user = bank.GetUser(userId);
        if(user == null){
            System.out.println("User is not client of a bank. You can register by running command -crateUser");
            return;
        }

        System.out.println("Please, enter address: ");
        String address = in.nextLine();

        user.SetAddress(address);
    }
}
