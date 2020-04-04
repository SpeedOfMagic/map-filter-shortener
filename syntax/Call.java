package syntax;

import error.SyntaxError;

public abstract class Call {
    Expression expression;

    Call(Expression expression) { this.expression = expression; }

    public Expression getExpression() { return expression; }

    public static Call valueOf(String call) throws SyntaxError {
        if (call.startsWith(FilterCall.KEYWORD))
            return FilterCall.valueOf(call);
        else if (call.startsWith(MapCall.KEYWORD))
            return MapCall.valueOf(call);
        else
            throw new SyntaxError("Call object doesn't have proper keyword");
    }

    @Override
    public String toString() {
        if (this instanceof FilterCall) {
            FilterCall call = (FilterCall) this;
            return call.toString();
        } else if (this instanceof MapCall) {
            MapCall call = (MapCall) this;
            return call.toString();
        } else {
            throw new IllegalStateException();
        }
    }
}