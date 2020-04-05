package test;
import converter.Converter;
import error.SyntaxError;
import error.TypeError;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import syntax.CallChain;
import syntax.Element;
import syntax.FilterCall;

public class ConverterTest {

    /**
     * Test Converter::convert function with given input
     * 1. It gets result of Converter.convert(input)
     * 2. It tries to make call chain object from it
     * 3. It checks if resulting call chain if equal to the result of Converter::convert from that call chain
     *
     * @param input given input
     */
    private void convert(String input) {
        String result = Converter.convert(input).toString();
        CallChain callChain = CallChain.valueOf(result);
        Assertions.assertEquals(callChain.toString(), Converter.convert(callChain.toString()).toString());
    }

    @Test
    public void convertCorrect() {  // Test to get correct answers
        convert("filter{(element>10)}%>%filter{(element<20)}");
        convert("map{(element+10)}%>%filter{(element>10)}%>%map{(element*element)}");
        convert("filter{(element>0)}%>%filter{(element<0)}%>%map{(element*element)}");
        convert("filter{(element>0)}%>%map{((element*element)+((element*20)+100))}");
        convert("filter{(1=0)}%>%map{element}");
        convert("map{(element*element)}%>%map{(element*element)}%>%map{(element*element)}");
        convert("filter{(1=0)}");
        convert("map{(element+5)}");
        convert("map{(element+-5)}");
        convert("map{-7}");
    }

    @Test
    public void convertSyntaxError() {  // Tests to get SYNTAX ERROR
        Assertions.assertThrows(SyntaxError.class,
                () -> convert(""));
        Assertions.assertThrows(SyntaxError.class,
                () -> convert("filter{(element>0)}%>%filter{(element<0)}%>%map{(element*element)"));
        Assertions.assertThrows(SyntaxError.class,
                () -> convert("filter{()}"));
        Assertions.assertThrows(SyntaxError.class,
                () -> convert("filter{}"));
        Assertions.assertThrows(SyntaxError.class,
                () -> convert("%>%>%"));
        Assertions.assertThrows(SyntaxError.class,
                () -> convert("%>%"));
        Assertions.assertThrows(SyntaxError.class,
                () -> convert("%>%%>%"));
        Assertions.assertThrows(SyntaxError.class,
                () -> convert("42"));
        Assertions.assertThrows(SyntaxError.class,
                () -> convert("map{(element>1)*(element<1)}"));
    }

    @Test
    public void convertTypeError() {  // Tests to get TYPE ERROR
        Assertions.assertThrows(TypeError.class,
                () -> convert("filter{(element&element)}"));
        Assertions.assertThrows(TypeError.class,
                () -> convert("filter{(element*element)}"));
        Assertions.assertThrows(TypeError.class,
                () -> convert("map{((element>1)*(element<1))}"));
        Assertions.assertThrows(TypeError.class,
                () -> convert("map{((element>1)&(element<1))}"));
        Assertions.assertThrows(TypeError.class,
                () -> convert("filter{((element>1)&element)}"));
        Assertions.assertThrows(TypeError.class,
                () -> convert("filter{element}"));
        Assertions.assertThrows(TypeError.class,
                () -> convert("filter{-42}"));
    }
}