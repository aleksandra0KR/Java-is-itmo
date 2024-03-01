package aleksandra0KR.Picocli;

import picocli.CommandLine;

@CommandLine.Command(name = "-help", description = "Prints all commands")
public class HelpCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("Commands:");
        System.out.println("-createBank");
        System.out.println("-createUser");
        System.out.println("-createAccount");
        System.out.println("-addAddress");
        System.out.println("-addPassport");
        System.out.println("-transaction");
        System.out.println("-cancelOperation");
        System.out.println("-stop");
    }
}