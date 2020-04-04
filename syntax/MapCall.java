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

    public void appendModification(Expression modification) {
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
