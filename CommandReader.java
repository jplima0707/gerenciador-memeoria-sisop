import Entities.Command;
import Entities.MMU;
import Entities.Process;

import java.io.*;
import java.util.regex.*;

public class CommandReader {
    private final MMU mmu;

    public CommandReader(MMU mmu) {
        this.mmu = mmu;
    }

    public void executeFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                Command cmd = parseCommand(line);
                if (cmd == null) {
                    System.out.println("Invalid input: " + line);
                    continue;
                }

                executeCommand(cmd);
                printMemoryState();
            }
        } catch (IOException e) {
            System.out.println("Error while reading: " + e.getMessage());
        }
    }

    private Command parseCommand(String line) {
        Pattern inPattern = Pattern.compile("IN\\((\\w+),\\s*(\\d+)\\)", Pattern.CASE_INSENSITIVE);
        Pattern outPattern = Pattern.compile("OUT\\((\\w+)\\)", Pattern.CASE_INSENSITIVE);

        Matcher inMatcher = inPattern.matcher(line);
        Matcher outMatcher = outPattern.matcher(line);

        if (inMatcher.matches()) {
            String id = inMatcher.group(1);
            int size = Integer.parseInt(inMatcher.group(2));
            return new Command(Command.Type.IN, id, size);
        } else if (outMatcher.matches()) {
            String id = outMatcher.group(1);
            return new Command(Command.Type.OUT, id, 0);
        }
        return null;
    }

    private void executeCommand(Command cmd) {
        switch (cmd.getType()) {
            case IN -> {
                mmu.allocate(new Process(cmd.getSize(), cmd.getProcessId()));
            }
            case OUT -> {
                mmu.free(new Process(cmd.getSize(), cmd.getProcessId()));
            }
        }
    }

    private void printMemoryState() {
        System.out.println("\nMemory State:");
        Process current = mmu.getMemory().getHead();
        if (current == null) {
            System.out.println("Empty.");
        } else {
            while (current != null) {
                System.out.printf(" - Process %s (start=%d, size=%d)%n",
                        current.getId(), current.getStart(), current.getSize());
                current = current.getNext();
            }
        }
        System.out.println("-------------------------------\n");
    }
}
