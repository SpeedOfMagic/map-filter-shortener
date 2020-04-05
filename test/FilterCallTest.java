package test;

import error.TypeError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import syntax.*;

class FilterCallTest {

    @Test
    void appendCondition() {
        FilterCall filterCall = new FilterCall();
        Assertions.assertEquals("filter{(1=1)}", filterCall.toString());

        filterCall.appendCondition(BinaryExpression.valueOf("(element>1)"));
        Assertions.assertEquals("filter{(element>1)}", filterCall.toString());

        filterCall.appendCondition(BinaryExpression.valueOf("(element<1)"));
        Assertions.assertEquals("filter{((element>1)&(element<1))}", filterCall.toString());

        filterCall.appendCondition(BinaryExpression.valueOf("((element+element)>0)"));
        Assertions.assertEquals("filter{(((element>1)&(element<1))&((element+element)>0))}",
                                filterCall.toString());
        Assertions.assertThrows(TypeError.class, () -> filterCall.appendCondition(
                                                                  BinaryExpression.valueOf("(element+element)")
        ));
    }

    @Test
    void testToString() {
        Assertions.assertEquals("filter{(element>element)}",
                new FilterCall(new BinaryExpression(new Element(), Operator.MORE, new Element())).toString()
        );
        Assertions.assertEquals("filter{(1=element)}",
            new FilterCall(
                new BinaryExpression(ConstantExpression.valueOf("1"), Operator.EQUAL, new Element())
            ).toString()
        );
    }
}