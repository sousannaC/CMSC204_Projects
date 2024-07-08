//Sousanna Chugunova
//Dr.Thai
//CMSC204

package notation;

import java.util.Stack;

public class Notation {

    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
        if (infix == null || infix.isEmpty()) {
            throw new InvalidNotationFormatException("Infix expression is invalid");
        }

        StringBuilder output = new StringBuilder();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isWhitespace(c)) {
                // Ignore spaces
                continue;
            } else if (Character.isDigit(c)) {
                // If the character is a digit, append it to the output
                output.append(c);
               
            } else if (c == '(') {
            
                // If the character is '(', push it onto the stack
                operators.push(c);
            } else if (isOperator(c)) {
                // Pop operators from the stack to the output while they have
                // equal or higher precedence than the current operator
                while (!operators.isEmpty() && isOperator(operators.peek()) &&
                        precedence(c) <= precedence(operators.peek())) {
                    output.append(operators.pop());
                    output.append(' ');

                }
                // Push the current operator onto the stack
                operators.push(c);
            } else if (c == ')') {
                // If the character is ')', pop from the stack to the output until '(' is found
                while (!operators.isEmpty() && operators.peek() != '(') {
                    output.append(operators.pop());
                }
                if (operators.isEmpty() || operators.peek() != '(') {
                    throw new InvalidNotationFormatException("Mismatched parentheses");
                }
                operators.pop(); // Discard the '('
            } else {
                throw new InvalidNotationFormatException("Invalid character in infix expression");
            }
        }

        // Pop any remaining operators from the stack to the output
        while (!operators.isEmpty()) {
            char top = operators.pop();
            if (top == '(' || top == ')') {
                throw new InvalidNotationFormatException("Mismatched parentheses");
            }
            output.append(top);
        }

        return output.toString();
    }

    private static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0; // For characters that are not operators
        }
    }
    
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
        if (postfix == null || postfix.isEmpty()) {
            throw new InvalidNotationFormatException("Postfix expression is invalid");
        }

        Stack<String> stack = new Stack<>();
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (Character.isWhitespace(c)) {
                // Ignore spaces
                continue;
            } else if (Character.isDigit(c)) {
                // If the character is an operand, push it onto the stack
                stack.push(String.valueOf(c));
            } else if (isOperator(c)) {
                // Pop the top 2 values from the stack
                if (stack.size() < 2) {
                    throw new InvalidNotationFormatException("Invalid postfix expression");
                }
                String operand2 = stack.pop();
                String operand1 = stack.pop();

                // Create a string with the operator in between the operands
                String newExpr = "(" + operand1 + c + operand2 + ")";
                // Push the resulting string back to the stack
                stack.push(newExpr);
            } else {
                throw new InvalidNotationFormatException("Invalid character in postfix expression");
            }
        }

        // After reading the entire postfix expression, there should be exactly one value in the stack
        if (stack.size() != 1) {
            throw new InvalidNotationFormatException("Invalid postfix expression");
        }

        return stack.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }


	public static int evaluatePostfixExpression(String postfix) throws InvalidNotationFormatException {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (Character.isWhitespace(c)) {
                // Ignore spaces
                continue;
            } else if (Character.isDigit(c)) {
                // If the character is a digit, convert it to integer and push to stack
                stack.push(Character.getNumericValue(c));
            } else if (isOperator(c)) {
                // Pop the top 2 values from the stack
                if (stack.size() < 2) {
                    throw new InvalidNotationFormatException("Invalid postfix expression");
                }
                int operand2 = stack.pop();
                int operand1 = stack.pop();

                // Perform the operation and push the result back onto the stack
                int result;
                switch (c) {
                    case '+':
                        result = operand1 + operand2;
                        break;
                    case '-':
                        result = operand1 - operand2;
                        break;
                    case '*':
                        result = operand1 * operand2;
                        break;
                    case '/':
                        result = operand1 / operand2;
                        break;
                    case '^':
                        result = (int) Math.pow(operand1, operand2);
                        break;
                    default:
                        throw new InvalidNotationFormatException("Invalid operator");
                }
                stack.push(result);
            } else {
                throw new InvalidNotationFormatException("Invalid character in postfix expression");
            }
        }

        // After processing the entire postfix expression, the final result should be on the stack
        if (stack.size() != 1) {
            throw new InvalidNotationFormatException("Invalid postfix expression");
        }

        return stack.pop();
    }
}