package converter;

import error.SyntaxError;
import error.TypeError;
import syntax.*;

public class Converter {
    public static CallChain convert(String expression) throws SyntaxError, TypeError {
        CallChain callChain = CallChain.valueOf(expression);

        FilterCall resultFilterCall = new FilterCall();
        MapCall resultMapCall = new MapCall(new Element());
        Expression currentElement = new Element();

        for (Call call : callChain) {
            if (call instanceof FilterCall) {
                call.getExpression().changeElementTo(currentElement);
                resultFilterCall.appendCondition(call.getExpression());
            } else {
                resultMapCall.appendModification(call.getExpression());
                call.getExpression().changeElementTo(currentElement);
                currentElement = call.getExpression();
            }
        }

        return CallChain.of(resultFilterCall, resultMapCall);
    }
}
