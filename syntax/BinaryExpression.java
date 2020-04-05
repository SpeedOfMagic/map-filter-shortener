package syntax;

import error.SyntaxError;
import error.TypeError;

public class BinaryExpression extends Expression {
    private Expression expression1;
    private final Operator operator;
    private Expression expression2;

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

    /**
     * Changes all occurrences of "element" into given expression.
     * Let's look at each expression E. If E is "element", then we simply change it.
     * Otherwise we call changeElementTo(expression) in E.
     *
     * @param expression given expression
     */
    public void changeElementTo(Expression expression) {
        if (expression1 instanceof Element)
            expression1 = expression;
        else
            expression1.changeElementTo(expression);
        if (expression2 instanceof Element)
            expression2 = expression;
        else
            expression2.changeElementTo(expression);
    }

    /**
     * Converts given expression into BinaryExpression.
     * First we remove brackets from each side. Then we need to find operator of it.
     * To do that, we keep "depth" of a current symbol - amount of opened brackets before it.
     * Let's go through every symbol that is an operator with index > 0.
     * If depth == 0 (and we select the first symbol with depth == 0) - then it's a required operator.
     * If depth != 0 - then it's not a required operator but an operator of one of inner BinaryExpressions.
     * It's important to have first symbol with index > 0 as an operator since
     * there can be a constant expression with minus.
     *
     * @param expression given expression
     * @return resulting binary expression.
     */
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
