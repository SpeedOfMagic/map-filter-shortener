package test;

import error.TypeError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import syntax.*;

class MapCallTest {

    @Test
    void applyModification() {
        MapCall mapCall = new MapCall(new Element());
        Assertions.assertEquals("map{element}", mapCall.toString());

        mapCall.applyModification(BinaryExpression.valueOf("(element+1)"));
        Assertions.assertEquals("map{(element+1)}", mapCall.toString());

        mapCall.applyModification(BinaryExpression.valueOf("(element+-1)"));
        Assertions.assertEquals("map{((element+1)+-1)}", mapCall.toString());

        mapCall.applyModification(BinaryExpression.valueOf("((element*element)+element)"));
        Assertions.assertEquals("map{((((element+1)+-1)*((element+1)+-1))+((element+1)+-1))}",
                mapCall.toString());
        Assertions.assertThrows(TypeError.class, () -> mapCall.applyModification(
                BinaryExpression.valueOf("(element>element)")
        ));
    }

    @Test
    void testToString() {
        Assertions.assertEquals("map{(element+element)}",
                new MapCall(new BinaryExpression(new Element(), Operator.PLUS, new Element())).toString()
        );
        Assertions.assertEquals("map{(1-element)}",
                new MapCall(
                        new BinaryExpression(ConstantExpression.valueOf("1"), Operator.MINUS, new Element())
                ).toString()
        );
    }
}