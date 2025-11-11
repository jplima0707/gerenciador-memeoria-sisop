package Entities;

public class Process {
    private int start;
    private int size;
    private String id;
    private Process next;

    public Process(int size, String id) {
        this.id = id;
        this.start = -1;
        this.size = size;
        this.next = null;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String s){
        this.id = s;
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

}
