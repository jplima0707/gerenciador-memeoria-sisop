package Strategies;

import Entities.Memory;
import Entities.Process;

public class WorstFitStrategy implements IAllocationStrategy {

	@Override
	public void allocate(Process process, Memory memory) {
		Process worstPrev = null;
        int worstStart = -1;
        int worstGap = -1;

        Process prev = null;
        Process current = memory.getHead();
        int start = 0;

        while (current != null) {
            int gap = current.getStart() - start;
            if (gap >= process.getSize() && gap > worstGap) {
                worstGap = gap;
                worstStart = start;
                worstPrev = prev;
            }
            start = current.getStart() + current.getSize();
            prev = current;
            current = current.getNext();
        }

        int total = memory.getTotalSize();
        if (total - start >= process.getSize() && (total - start) > worstGap) {
            worstStart = start;
            worstPrev = prev;
        }

        if (worstStart != -1) {
            insert(memory, worstPrev, process);
            System.out.println("Process " + process.getId() + " allocated");
			return;
        }
        System.out.println("Process " + process.getId() + " cannot be allocated");
    }

    private void insert(Memory memory, Process prev, Process p) {
        if (prev == null) {
            p.setNext(memory.getHead());
			p.setStart(0);
            memory.setHead(p);
        } else {
            p.setNext(prev.getNext());
			p.setStart(prev.getStart() + prev.getSize());
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
				return;
            }
            prev = cur;
            cur = cur.getNext();
        }
        System.out.println("Process " + process.getId() + " not found.");
    } 
}
