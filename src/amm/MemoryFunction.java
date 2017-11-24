package amm;

public class MemoryFunction {

    private Character stringIn;
    private char memoryIn;
    private String memoryOut;

    public MemoryFunction(Character stringIn, char memoryIn, String memoryOut) {
        this.stringIn = stringIn;
        this.memoryIn = memoryIn;
        this.memoryOut = memoryOut;
    }

    public Character getStringIn() {
        return stringIn;
    }

    public char getMemoryIn() {
        return memoryIn;
    }

    public String getMemoryOut() {
        return memoryOut;
    }
}
