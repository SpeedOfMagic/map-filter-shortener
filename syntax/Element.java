package syntax;

import error.SyntaxError;

public class Element extends Expression {
    public static final String ELEMENT = "element";

    public static Element valueOf(String element) {
        if (element.equals(ELEMENT))
            return new Element();
        else
            throw new SyntaxError("It's not element");
    }

    @Override
    public String toString() { return ELEMENT; }
}