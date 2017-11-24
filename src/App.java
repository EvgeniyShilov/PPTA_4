import amm.AMMControlUnit;
import amm.MemoryFunction;
import eamm.EAMMControlUnit;
import eamm.EMemoryFunction;
import grammar.model.Grammar;
import grammar.util.GrammarUtil;

import java.io.IOException;
import java.io.PrintStream;

public class App {

    public static void main(String[] args) throws IOException {
        ammTest();
    }

    private static void ammTest() throws IOException {
        Grammar grammar = GrammarUtil.fromFile("grammar1.txt");
        AMMControlUnit unit = new AMMControlUnit(grammar);
        for (MemoryFunction function : unit.getFunctions())
            System.out.println(function.getStringIn() + " " + function.getMemoryIn() + " " + function.getMemoryOut());
        String string = "a/b/a/**//*";
        String memory = grammar.getS();
        System.setOut(new PrintStream("output.txt"));
        System.out.println(unit.recognize(string, memory, new AMMControlUnit.StackParams(24)));
    }

    private static void eammTest() throws IOException {
        Grammar grammar = GrammarUtil.fromFile("grammar.txt");
        EAMMControlUnit unit = new EAMMControlUnit(grammar);
        for (EMemoryFunction function : unit.getFunctions())
            System.out.println(function.getStringIn() + " " + function.getMemoryIn() + " " + function.getMemoryOut());
        String string = "(a)";
        String memory = "#";
        System.setOut(new PrintStream("output.txt"));
        System.out.println(unit.recognize(string, memory, new EAMMControlUnit.StackParams(24)));
    }
}
