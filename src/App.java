import amm.AMMControlUnit;
import amm.MemoryFunction;
import grammar.model.Grammar;
import grammar.util.GrammarUtil;

import java.io.IOException;
import java.io.PrintStream;

public class App {

    public static void main(String[] args) throws IOException {
        Grammar grammar = GrammarUtil.fromFile("grammar.txt");
        AMMControlUnit unit = new AMMControlUnit(grammar);
        String string = "(a)";
        String memory = "S";
        System.setOut(new PrintStream("output.txt"));
        System.out.println(unit.recognize(string, memory));
    }
}
