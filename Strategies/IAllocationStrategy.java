package Strategies;

import Entities.Memory;
import Entities.Process;

public interface IAllocationStrategy {
    public void allocate(Process process, Memory memory);
    public void free(Process process, Memory memory);
}
