package aleksandra0KRBank.Console.Interface.Commands;

import picocli.CommandLine;

@CommandLine.Command(name = "-stop", description = "Stops program")
public class StopCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Running is finished");
        System.exit(0);
    }
}
