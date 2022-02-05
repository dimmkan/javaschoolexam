package com.tsystems.javaschool.tasks.calculator;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calculator {

    private Stack<String> stack = new Stack<>();
    private Stack<String> out = new Stack<>();
    DecimalFormat decimalFormat = new DecimalFormat("##.####");

    private boolean isNumber(String token){
        try {
            Double.parseDouble(token);
        } catch (Exception e) {

            return false;
        }
        return true;
    }

    private static int operationPriority(String token){
        if (token.equals("+") || token.equals("-")){
            return 1;
        }
        else if (token .equals("*") || token.equals("/")) {
            return 2;
        }
        else return 0;
    }

    private static boolean isOperator(String token){
        return operationPriority(token) > 0;
    }

    private boolean isOpenBrackets(String token){
        return token.equals("(");
    }

    private boolean isCloseBrackets(String token){
        return token.equals(")");
    }

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        // TODO: Implement the logic here
        return calculateStack(prepareStack(statement));
    }

    private Stack<String> prepareStack(String evaluation) {
        try{
            evaluation = evaluation.replace(" ","").replace("(-", "(0-").replace(",-", ",0-");
            StringTokenizer stringTokenizer = new StringTokenizer(evaluation,"+*-/()",true);
            stack.clear();
            out.clear();
            while (stringTokenizer.hasMoreTokens()){
                String token = stringTokenizer.nextToken();
                if (isOpenBrackets(token)){
                    stack.push(token);
                }
                else if (isCloseBrackets(token)){
                    while (!stack.empty() && !isOpenBrackets(stack.lastElement())){
                        out.push(stack.pop());
                    }
                    stack.pop();
                }
                else if (isNumber(token)){
                    out.push(token);
                }
                else if (isOperator(token)){
                    while (!stack.empty() && isOperator(stack.lastElement()) && operationPriority(stack.peek()) >= operationPriority(token)){
                        out.push(stack.pop());
                    }
                    stack.push(token);
                }
            }
            while (!stack.empty()){
                out.push(stack.pop());
            }
            return out;
        }catch (Exception e){
            return null;
        }
    }

    private String calculateStack (Stack<String> out){
        if(out!=null){
            stack.clear();
            for (String token : out){
                if (isNumber(token)){
                    stack.push(token);
                }else if (isOperator(token)){
                    if(token.equals("+")){
                        Double result = Double.parseDouble(stack.pop()) + Double.parseDouble(stack.pop());
                        stack.push(result.toString());
                    }
                    else if(token.equals("-")){
                        Collections.reverse(stack);
                        Double result = Double.parseDouble(stack.pop()) - Double.parseDouble(stack.pop());
                        stack.push(result.toString());
                    }else if(token.equals("*")){
                        Double result = Double.parseDouble(stack.pop()) * Double.parseDouble(stack.pop());
                        stack.push(result.toString());
                    }
                    else if(token.equals("/")){
                        Collections.reverse(stack);
                        Double result = Double.parseDouble(stack.pop()) / Double.parseDouble(stack.pop());
                        stack.push(result.toString());
                    }
                }
                else if(isOpenBrackets(token) || isCloseBrackets(token)){
                    return null;
                }
            }
            return decimalFormat.format(Double.parseDouble(stack.pop()));
        }else{
            return null;
        }
    }
}
