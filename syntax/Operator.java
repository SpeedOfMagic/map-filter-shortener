package syntax;

import error.SyntaxError;

public enum Operator {
    PLUS,
    MINUS,
    MULTIPLY,
    MORE,
    LESS,
    EQUAL,
    AND,
    OR;

    @Override
    public String toString() {
        switch (this) {
            case PLUS: return "+";
            case MINUS: return "-";
            case MULTIPLY: return "*";
            case MORE: return ">";
            case LESS: return "<";
            case EQUAL: return "=";
            case AND: return "&";
            case OR: return "|";
        }

        throw new IllegalStateException();
    }

    public static Operator valueOf(char operator) {
        switch (operator) {
            case '+': return PLUS;
            case '-': return MINUS;
            case '*': return MULTIPLY;
            case '>': return MORE;
            case '<': return LESS;
            case '=': return EQUAL;
            case '&': return AND;
            case '|': return OR;
            default: throw new SyntaxError("No such operation was found");
        }
    }

    public static boolean isOperator(char c) { return "+-*><=&|".contains(String.valueOf(c)); }
}
