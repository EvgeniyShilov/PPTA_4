package eamm;

import grammar.model.Grammar;
import grammar.model.GrammarType;
import grammar.model.Rule;
import grammar.util.GrammarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EAMMControlUnit {

    private List<EMemoryFunction> functions;

    public EAMMControlUnit(Grammar grammar) {
        GrammarType type = GrammarUtil.getType(grammar);
        if (type != GrammarType.CONTEXT_FREE && type != GrammarType.REGULAR) throw new IllegalArgumentException();
        List<Rule> rules = grammar.getRules();
        functions = new ArrayList<>();
        for (Rule rule : rules) functions.add(new EMemoryFunction(null, rule.getRight(), rule.getLeft().charAt(0)));
        for (char t : grammar.getT()) functions.add(new EMemoryFunction(t, "", t));
        functions.add(new EMemoryFunction(null, "#S", null));
    }

    public boolean recognize(String string, String memory, StackParams params) {
        if (params != null && params.index > params.limit) {
            params.index--;
            return false;
        }
        if (memory.isEmpty() && string.isEmpty()) {
            System.out.println("String: '" + string + "' Memory: '" + memory + "'");
            if (params != null) params.index--;
            return true;
        }
        List<EMemoryFunction> availableFunctions = getFunctionsByInput(string.isEmpty() ? null : string.charAt(0), memory);
        for (EMemoryFunction function : availableFunctions) {
            try {
                if (params != null) params.index++;
                String memoryPostfix = function.getMemoryIn();
                if (memory.endsWith(memoryPostfix)) memory = memory.substring(0, memory.length() - memoryPostfix.length()) + function.getMemoryOut();
                if (Objects.equals(memory, "null")) memory = "";
                if (recognize(function.getStringIn() == null ? string : string.substring(1),
                        memory, params)) {
                    System.out.println("String: '" + string + "' Memory: '" + memory + "'");
                    if (params != null) params.index--;
                    return true;
                }
            } catch (StackOverflowError ignored) {
            }
        }
        if (params != null) params.index--;
        return false;
    }

    public List<EMemoryFunction> getFunctionsByInput(Character stringIn, String memoryIn) {
        List<EMemoryFunction> result = new ArrayList<>();
        for (EMemoryFunction function : functions)
            if (function.getStringIn() == null && memoryIn.endsWith(function.getMemoryIn()))
                result.add(function);
            else if (Objects.equals(function.getMemoryIn(), "") && function.getStringIn() == stringIn)
                result.add(function);
        return result;
    }

    public List<EMemoryFunction> getFunctions() {
        return functions;
    }

    public static class StackParams {

        public int index = 1;
        public int limit;

        public StackParams(int limit) {
            this.limit = limit;
        }
    }
}
