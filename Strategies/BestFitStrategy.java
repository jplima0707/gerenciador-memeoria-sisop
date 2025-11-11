package Strategies;

import Entities.Memory;
import Entities.Process;

public class BestFitStrategy implements IAllocationStrategy {

    @Override
    public void allocate(Process process, Memory memory) {
        Process bestPrev = null;
        int bestStart = -1;
        int bestGap = Integer.MAX_VALUE;

        Process prev = null;
        Process current = memory.getHead();
        int start = 0;

        while (current != null) {
            int gap = current.getStart() - start;
            if (gap >= process.getSize() && gap < bestGap) {
                bestGap = gap;
                bestStart = start;
                bestPrev = prev;
            }
            start = current.getStart() + current.getSize();
            prev = current;
            current = current.getNext();
        }

        int total = memory.getTotalSize();
        if (total - start >= process.getSize() && (total - start) < bestGap) {
            bestStart = start;
            bestPrev = prev;
        }

        if (bestStart != -1) {
            insert(memory, bestPrev, process);
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
