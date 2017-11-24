package eamm;

public class EMemoryFunction {

    private Character stringIn;
    private String memoryIn;
    private Character memoryOut;

    public EMemoryFunction(Character stringIn, String memoryIn, Character memoryOut) {
        this.stringIn = stringIn;
        this.memoryIn = memoryIn;
        this.memoryOut = memoryOut;
    }

    public Character getStringIn() {
        return stringIn;
    }

    public String getMemoryIn() {
        return memoryIn;
    }

    public Character getMemoryOut() {
        return memoryOut;
    }
}
