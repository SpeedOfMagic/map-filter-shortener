import converter.Converter;
import error.SyntaxError;
import error.TypeError;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String expression = input.nextLine();
        try {
            String convertedExpression = Converter.convert(expression).toString();
            System.out.println(convertedExpression);
        } catch (SyntaxError syntaxError) {
            System.out.println("SYNTAX ERROR");
            // throw syntaxError;
        } catch (TypeError typeError) {
            System.out.println("TYPE ERROR");
            // throw typeError;
        }
    }
}
