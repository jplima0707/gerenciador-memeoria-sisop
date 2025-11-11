import Entities.MMU;
import Entities.MMUFactory;
import Entities.Memory;

public class Main {
    public static void main(String[] args) {
        Memory memory = new Memory(1024);
        MMU mmu = MMUFactory.create(memory, "worst-fit");
        CommandReader reader = new CommandReader(mmu);
        reader.executeFromFile("./input.txt");   
    }
}
