package Entities;

public class MMU {
    private final Memory memory;
    private final AllocationStrategy strategy;

    public MMU(Memory memory, AllocationStrategy strategy) {
        this.memory = memory;
        this.strategy = strategy;
    }

    public boolean allocate(int size) {
        return strategy.allocate(memory, size);
    }

    public void printMemoryState() {
        memory.printFreeBlocks();
    }

    public Memory getMemory() {
        return memory;
    }
}

