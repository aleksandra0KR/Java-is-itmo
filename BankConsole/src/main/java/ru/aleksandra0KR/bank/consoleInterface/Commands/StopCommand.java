package ru.aleksandra0KR.bank.consoleInterface.Commands;

import picocli.CommandLine;

/**
 * Command class for stop running
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
@CommandLine.Command(name = "-stop", description = "Stops program")
public class StopCommand implements Runnable {

  // Stops program
  @Override
  public void run() {
    System.out.println("Running is finished");
    System.exit(0);
  }
}
