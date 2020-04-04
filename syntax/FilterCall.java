package syntax;

import error.SyntaxError;
import error.TypeError;

public class FilterCall extends Call {
    public static final String KEYWORD = "filter";

    public FilterCall() { super(null); }
    public FilterCall(Expression expression) {
        super(expression);
        if (!expression.isBoolean)
            throw new TypeError("Filter must get boolean, got integer");
    }

    public void appendCondition(Expression condition) {
        if (expression == null)
            expression = condition;
        else
            expression = new BinaryExpression(expression, Operator.AND, condition);
    }

    public static FilterCall valueOf(String call) {
        if (!call.startsWith(KEYWORD))
            throw new SyntaxError("Wrong keyword for FilterCall");

        return new FilterCall(Expression.valueOf(call.substring(KEYWORD.length() + 1, call.length() - 1)));
    }

    @Override
    public String toString() {
        if (expression == null)
            return "filter{(1=1)}";
        return KEYWORD + "{" + expression.toString() + "}";
    }
}
