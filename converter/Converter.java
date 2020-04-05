package converter;

import error.SyntaxError;
import error.TypeError;
import syntax.*;

public class Converter {
    /**
     * Converts given expression into form filter{...}%>%map{...}
     * Let's keep current filter and map calls. Let's go through each object of call chain:
     * If call is FilterCall, then we change every "element" in call into result of current map
     * and append that condition to current resulting filter call.
     * If call is MapCall, then we change every "element" in map call into element
     *
     * @param expression - given expression
     * @return converted expression as a call chain
     * @throws SyntaxError if given expression contains syntax errors
     * @throws TypeError if given expression applies incorrect operations to incorrect types
     */
    public static CallChain convert(String expression) throws SyntaxError, TypeError {
        CallChain callChain = CallChain.valueOf(expression);
        FilterCall resultFilterCall = new FilterCall();
        MapCall resultMapCall = new MapCall(new Element());

        for (Call call : callChain) {
            if (call instanceof FilterCall) {
                call.getExpression().changeElementTo(resultMapCall.getExpression());
                resultFilterCall.appendCondition(call.getExpression());
            } else {
                resultMapCall.applyModification(call.getExpression());
            }
        }

        return CallChain.of(resultFilterCall, resultMapCall);
    }
}
