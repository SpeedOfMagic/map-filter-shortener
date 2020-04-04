package syntax;

import error.SyntaxError;

import java.math.BigInteger;

public class Number extends BigInteger {
    private Number(String val) { super(val); }

    public static Number valueOf(String number) {
        try {
            return new Number(number);
        } catch (NumberFormatException exception) {
            throw new SyntaxError("Passed string is not a number");
        }
    }
}
