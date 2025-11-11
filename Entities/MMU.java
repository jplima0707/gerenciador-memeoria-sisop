package Entities;

import Strategies.IAllocationStrategy;

public class MMU {
    private final Memory memory;
    private final IAllocationStrategy strategy;

    public MMU(Memory memory, IAllocationStrategy strategy) {
        this.memory = memory;
        this.strategy = strategy;
    }

    public void allocate(Process p) {
        strategy.allocate(p, memory);
    }

    public void free(Process p) {
        strategy.free(p, memory);
    }

    public Memory getMemory() {
        return memory;
    }
}

