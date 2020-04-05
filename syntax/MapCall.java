package syntax;

import error.SyntaxError;
import error.TypeError;

public class MapCall extends Call {
    public static final String KEYWORD = "map";

    public MapCall(Expression expression) {
        super(expression);
        if (expression.isBoolean)
            throw new TypeError("Map must get integer, got boolean");
    }

    /**
     * Applies given modification to current map call.
     * Simply change all occurrences of "element" in modification to current expression
     * and that modification is a result.
     *
     * @param modification given modification
     */
    public void applyModification(Expression modification) {
        if (modification.isBoolean)
            throw new TypeError("Modification must return integer, returned boolean");
        modification.changeElementTo(expression);
        expression = modification;
    }

    public static MapCall valueOf(String call) {
        if (!call.startsWith(KEYWORD))
            throw new SyntaxError("Wrong keyword for MapCall");
        return new MapCall(Expression.valueOf(call.substring(KEYWORD.length() + 1, call.length() - 1)));
    }

    @Override
    public String toString() {
        return KEYWORD + "{" + expression.toString() + "}";
    }
}
