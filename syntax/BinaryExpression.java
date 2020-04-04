package syntax;

import error.SyntaxError;
import error.TypeError;

public class BinaryExpression extends Expression {
    Expression expression1;
    final Operator operator;
    Expression expression2;

    public BinaryExpression(Expression expression1, Operator operator, Expression expression2) {
        this.expression1 = expression1;
        this.operator = operator;
        this.expression2 = expression2;
        switch (this.operator) {
            case PLUS:
            case MINUS:
            case MULTIPLY:
                if (!(!expression1.isBoolean && !expression2.isBoolean))
                    throw new TypeError("Expressions don't pass integer types");
                isBoolean = false;
                return;

            case MORE:
            case LESS:
            case EQUAL:
                if (!(!expression1.isBoolean && !expression2.isBoolean))
                    throw new TypeError("Expressions don't pass integer types");
                isBoolean = true;
                return;

            case AND:
            case OR:
                if (!(expression1.isBoolean && expression2.isBoolean))
                    throw new TypeError("Expressions don't pass boolean types");
                isBoolean = true;
        }
    }

    public void changeElementTo(Expression expression) {
        //TODO Написать Clone ко всем объектам
        if (expression1 instanceof Element)
            expression1 = expression;
        if (expression2 instanceof Element)
            expression2 = expression;
    }

    public static BinaryExpression valueOf(String expression) {
        if (!(expression.startsWith("(") && expression.endsWith(")")))
            throw new SyntaxError("Binary expression should have brackets");
        expression = expression.substring(1, expression.length() - 1);

        int depth = 0;
        for (int i = 0; i < expression.length(); ++i) {
            if (expression.charAt(i) == '(')
                ++depth;
            else if (expression.charAt(i) == ')')
                --depth;
            if (i > 0 && depth == 0 && Operator.isOperator(expression.charAt(i)))
                return new BinaryExpression(Expression.valueOf(expression.substring(0, i)),
                                            Operator.valueOf(expression.charAt(i)),
                                            Expression.valueOf(expression.substring(i + 1)));
        }

        throw new SyntaxError("No operator found");
    }

    @Override
    public String toString() {
        return "(" + expression1.toString() + operator.toString() + expression2.toString() + ")";
    }
}
