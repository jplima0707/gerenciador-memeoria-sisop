package Entities;

public class Process {
    private int start;
    private final int id;
    private static int aux = 0;
    private int size;
    private Process next;

    public Process(int start, int size) {
        this.id = aux;
        this.start = start;
        this.size = size;
        this.next = null;
        aux++;
    }
    
    public int getId() {
        return id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Process getNext() {
        return next;
    }

    public void setNext(Process next) {
        this.next = next;
    }

    public static void resetCounter() {
        aux = 0;
    }
}
