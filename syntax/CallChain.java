package syntax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CallChain extends ArrayList<Call> {
    public static final String SEPARATOR = "%>%";

    public CallChain(List<Call> list) { super(list); }

    public static CallChain of(Call call1, Call call2) {
        return new CallChain(List.of(call1, call2));
    }

    public static CallChain valueOf(String callChain) {
        return new CallChain(Arrays.stream(callChain.split(SEPARATOR, -1))
                   .map(Call::valueOf)
                   .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        boolean firstCall = true;
        for (Call call : this) {
            if (!firstCall)
                result.append(SEPARATOR);
            result.append(call.toString());
            firstCall = false;
        }
        return result.toString();
    }
}
