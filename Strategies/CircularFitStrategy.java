package Strategies;

import Entities.Memory;
import Entities.Process;

public class CircularFitStrategy implements IAllocationStrategy {
	private Process lastAllocated = null;

	@Override
	public void allocate(Process process, Memory memory) {
		int total = memory.getTotalSize();

		if (memory.getHead() == null) {
			memory.setHead(process);
			lastAllocated = process;
			System.out.println("Process " + process.getId() + " allocated");
			return;
		}

		Process prev = null;
		Process current = (lastAllocated != null) ? lastAllocated : memory.getHead();
		boolean wrapped = false;

		int start = current.getStart() + current.getSize();
		if (start >= total) { 
			start = 0;
			current = memory.getHead();
			prev = null;
			wrapped = true;
		}

		while (true) {
			int gap = (current.getStart() - start);

			if (gap >= process.getSize()) {
				insert(memory, prev, process);
				lastAllocated = process;
				System.out.println("Process " + process.getId() + " allocated");
				return;
			}

			start = current.getStart() + current.getSize();
			prev = current;
			current = current.getNext();

			if (current == null && total - start >= process.getSize()) {
				insert(memory, prev, process);
				lastAllocated = process;
				System.out.println("Process " + process.getId() + " allocated");
				return;
			}

			if (current == null && !wrapped) {
				current = memory.getHead();
				prev = null;
				start = 0;
				wrapped = true;
			} else if (current == null && wrapped) {
				break;
			}
		}

		System.out.println("Process " + process.getId() + " cannot be allocated");
	}

    private void insert(Memory memory, Process prev, Process p) {
        if (prev == null) {
            p.setNext(memory.getHead());
			p.setStart(0);
            memory.setHead(p);
        } else {
			p.setStart(prev.getStart() + prev.getSize());
            p.setNext(prev.getNext());
            prev.setNext(p);
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
				if (lastAllocated == cur) lastAllocated = null;
				return;
            }
            prev = cur;
            cur = cur.getNext();
        }
        System.out.println("Process " + process.getId() + " not found.");
    } 
}
