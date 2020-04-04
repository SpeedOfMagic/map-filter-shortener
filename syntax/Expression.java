package syntax;

import error.SyntaxError;

public class Expression {
    protected boolean isBoolean = false;

    Expression() {}

    public void changeElementTo(Expression expression) {
        if (this instanceof BinaryExpression) {
            BinaryExpression result = (BinaryExpression) this;
            result.changeElementTo(expression);
        }
    }

    public static Expression valueOf(String expression) {
        try {
            return Element.valueOf(expression);
        } catch (SyntaxError syntaxError1) {
            try {
                return ConstantExpression.valueOf(expression);
            } catch (SyntaxError syntaxError2) {
                return BinaryExpression.valueOf(expression);
            }
        }
    }

    @Override
    public String toString() {
        if (this instanceof Element) {
            Element element = (Element) this;
            return element.toString();
        } else if (this instanceof ConstantExpression) {
            ConstantExpression expression = (ConstantExpression) this;
            return expression.toString();
        } else if (this instanceof BinaryExpression) {
            BinaryExpression expression = (BinaryExpression) this;
            return expression.toString();
        } else {
            throw new IllegalStateException();
        }
    }
}
