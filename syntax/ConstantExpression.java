package syntax;

public class ConstantExpression extends Expression {
    private final boolean minus;
    private final Number number;

    public ConstantExpression(boolean minus, Number number) {
        this.minus = minus;
        this.number = number;
    }

    public static ConstantExpression valueOf(String expression) {
        if (expression.startsWith("-"))
            return new ConstantExpression(true, Number.valueOf(expression.substring(1)));
        else
            return new ConstantExpression(false, Number.valueOf(expression));
    }

    @Override
    public String toString() {
        return (minus ? "-" : "") + number.toString();
    }
}