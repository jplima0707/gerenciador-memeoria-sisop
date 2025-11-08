package Entities;

public class Memory{

    private int totalSize;
    private Process head;

    public Memory(int size){
        if ((size & (size - 1)) != 0 || size <= 0) {
            throw new IllegalArgumentException("Memory size must be a power of 2 and greater than 0");
        }
        this.totalSize = size;
        this.head = new Process(0, size);
    }

    public int getTotalSize(){
        return totalSize;
    }

    public void setTotalSize(int size){
        this.totalSize = size;
    }

    public Process getHead(){
        return head;
    }

    public void setHead(Process head){
        this.head = head;
    }
}
