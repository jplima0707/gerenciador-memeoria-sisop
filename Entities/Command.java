package Entities;

public class Command {
    public enum Type { IN, OUT }

    private final Type type;
    private final String processId;
    private final int size;

    public Command(Type type, String processId, int size) {
        this.type = type;
        this.processId = processId;
        this.size = size;
    }

    public Type getType() { 
        return type; 
    }
    
    public String getProcessId() { 
        return processId;
    }
    
    public int getSize() { 
        return size; 
    }
}

