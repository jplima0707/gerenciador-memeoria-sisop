package Strategies;

import Entities.Memory;
import Entities.Process;

public class FirstFitStrategy implements IAllocationStrategy {

	@Override
    public void allocate(Process process, Memory memory) {
        int total = memory.getTotalSize();
        Process prev = null;
        Process current = memory.getHead();
        int start = 0;

        while (current != null) {
            int gap = current.getStart() - start;
            if (gap >= process.getSize()) {
                insertProcess(memory, prev, process);
                System.out.println("Process " + process.getId() + " allocated");
				return;
            }
            start = current.getStart() + current.getSize();
            prev = current;
            current = current.getNext();
        }

        if (total - start >= process.getSize()) {
            insertProcess(memory, prev, process);
            System.out.println("Process " + process.getId() + " allocated");
			return;
        }

        System.out.println("Process " + process.getId() + " cannot be allocated");
    }

    private void insertProcess(Memory memory, Process prev, Process newProcess) {
        if (prev == null) {
            newProcess.setNext(memory.getHead());
			newProcess.setStart(0);
            memory.setHead(newProcess);
        } else {
            newProcess.setNext(prev.getNext());
			newProcess.setStart(prev.getStart() + prev.getSize());
            prev.setNext(newProcess);
        }
    }

	@Override
    public void free(Process process, Memory memory) {
        Process prev = null;
        Process cur = memory.getHead();
        while (cur != null) {
            if (cur.getId().equalsIgnoreCase(process.getId())) {
                if (prev == null) memory.setHead(cur.getNext());
                else prev.setNext(cur.getNext());
                System.out.println("Process " + process.getId() + " dealocated.");
				return;
            }
            prev = cur;
            cur = cur.getNext();
        }
        System.out.println("Process " + process.getId() + " not found.");
    } 
}
