package aleksandra0KR.Interface;

import aleksandra0KR.Picocli.*;
import picocli.CommandLine;

import java.util.*;

@CommandLine.Command(name = "myapp", subcommands = {StopCommand.class, HelpCommand.class, CreateUserCommand.class, CreateBankCommand.class, CreateAccountCommand.class, AddAddressCommand.class, AddPassportCommand.class, TransactionCommand.class, CancelTransactionCommand.class})
public class RunApp {
    private static final Map<String, Runnable> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("-stop", new StopCommand());
        COMMANDS.put("-help", new HelpCommand());
        COMMANDS.put("-createUser", new CreateUserCommand());
        COMMANDS.put("-createBank", new CreateBankCommand());
        COMMANDS.put("-createAccount", new CreateAccountCommand());
        COMMANDS.put("-addPassport", new AddPassportCommand());
        COMMANDS.put("-addAddress", new AddAddressCommand());
        COMMANDS.put("-transaction", new TransactionCommand());
        COMMANDS.put("-cancelTransaction", new CancelTransactionCommand());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandLine cmd = new CommandLine(new RunApp());

        while (true) {
            System.out.println("Enter a command:");
            String input = scanner.nextLine();

            COMMANDS.getOrDefault(input, () -> System.out.println("Invalid command. Please try again. Or Enter -help for list of commands")).run();
        }
    }
}

// TODO all UUID equals
