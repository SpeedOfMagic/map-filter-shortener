package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import syntax.BinaryExpression;
import syntax.ConstantExpression;
import syntax.Element;
import syntax.Operator;

class BinaryExpressionTest {

    @Test
    void changeElementTo() {
        BinaryExpression binaryExpression = new BinaryExpression(new Element(), Operator.MINUS, new Element());
        binaryExpression.changeElementTo(new BinaryExpression(new Element(),
                                         Operator.PLUS,
                                         ConstantExpression.valueOf("10")));
        Assertions.assertEquals("((element+10)-(element+10))", binaryExpression.toString());
    }

    @Test
    void testToString() {
        Assertions.assertEquals("(element*element)",
                new BinaryExpression(new Element(), Operator.MULTIPLY, new Element()).toString()
        );

        Assertions.assertEquals("(((element+element)*element)+1)",
                new BinaryExpression(
                        new BinaryExpression(
                            new BinaryExpression(new Element(), Operator.PLUS, new Element()),
                            Operator.MULTIPLY,
                            new Element()
                        ),
                        Operator.PLUS,
                        ConstantExpression.valueOf("1")
                ).toString()
        );
    }
}