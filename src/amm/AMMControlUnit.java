package amm;

import grammar.model.Grammar;
import grammar.model.GrammarType;
import grammar.model.Rule;
import grammar.util.GrammarUtil;

import java.util.ArrayList;
import java.util.List;

public class AMMControlUnit {

    private List<MemoryFunction> functions;

    public AMMControlUnit(Grammar grammar) {
        GrammarType type = GrammarUtil.getType(grammar);
        if (type != GrammarType.CONTEXT_FREE && type != GrammarType.REGULAR) throw new IllegalArgumentException();
        List<Rule> rules = grammar.getRules();
        functions = new ArrayList<>();
        for (Rule rule : rules) functions.add(new MemoryFunction(null, rule.getLeft().charAt(0), rule.getRight()));
        for (char t : grammar.getT()) functions.add(new MemoryFunction(t, t, ""));
    }

    public boolean recognize(String string, String memory) {
        if (string.isEmpty()) {
            System.out.println("String: '" + string + "' Memory: '" + memory + "'");
            return true;
        }
        List<MemoryFunction> availableFunctions = getFunctionsByInput(string.charAt(0), memory.charAt(0));
        for (MemoryFunction function : availableFunctions) {
            try {
                if (recognize(function.getStringIn() == null ? string : string.substring(1),
                        function.getMemoryOut() + memory.substring(1))) {
                    System.out.println("String: '" + string + "' Memory: '" + memory + "'");
                    return true;
                }
            } catch (StackOverflowError ignored) {
            }
        }
        return false;
    }

    public List<MemoryFunction> getFunctions() {
        return functions;
    }

    public List<MemoryFunction> getFunctionsByInput(Character stringIn, char memoryIn) {
        List<MemoryFunction> result = new ArrayList<>();
        for (MemoryFunction function : functions)
            if ((function.getStringIn() == null || stringIn == function.getStringIn()) &&
                    memoryIn == function.getMemoryIn())
                result.add(function);
        return result;
    }
}
