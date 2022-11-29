/*
 * Kyle Vinod
 * Project 5 - Arithmetic Expression Parse Tree Implementation
 * Project 5
 * This project builds a ParseTree from a string expression.
 * With that tree, it will evaluate what the answer to the problem was
 * and bring the tree back to its string format.
 */
package cmsc256;
import bridges.base.BinTreeElement;
import bridges.connect.Bridges;
import bridges.validation.RateLimitException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

public class Project5{
    public static BinTreeElement<String> buildParseTree(String expression) {
        //Making operator ArrayList to determine if token is an operator
        ArrayList<String> operators = new ArrayList<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        //To determine the root and to add leaves to the root
        BinTreeElement<String> root = new BinTreeElement<>();
        BinTreeElement<String> node = new BinTreeElement<>();
        String[] tokens = expression.split(" ");
        String boop = "";
        int num = 0;
        for (int i = 0 ; i < tokens.length; i++) {
            int bop = 0;
            int beep = 0;
            //Will throw exception if element is not part of list of elements
            if (!isNumeric(tokens[i]) && !operators.contains(tokens[i]) && !tokens[i].equals("(") && !tokens[i].equals(")")) {
                throw new IllegalArgumentException("Expression cannot be parsed. Current token is " + tokens[i]);
            }
            //This determines if it will have a long tree leave
            if (tokens[i].equals("(") && i != 0) {
                String sub = "";
                //Determines how long the tree leave is
                while (beep != 1) {
                    if (tokens[i].equals("(")) {
                        bop++;
                    }
                    else if (tokens[i].equals(")")) {
                        bop--;
                    }
                    sub += tokens[i];
                    sub += " ";
                    i++;
                    if (bop == 0) {
                        beep++;
                    }
                }
                //Gets tree leave
                num++;
                node = buildParseTree(sub);
            }
            //If tree leave is not long, then it will set it to left tree leave
            if (isNumeric(tokens[1])) {
                root.setLeft(new BinTreeElement<>(tokens[1]));
            }
            //Will determine where to set a tree leave
            if (isNumeric(tokens[i])) {
                if (num == 0) {
                    if (root.getValue() == null) {
                        boop = tokens[i];
                    } else {
                        root.setLeft(new BinTreeElement<>(boop));
                    }
                    if (root.getLeft() == null) {
                        root.setLeft(new BinTreeElement<>(tokens[i]));
                    } else {
                        root.setRight(new BinTreeElement<>(tokens[i]));
                    }
                }
                else if (root.getLeft() == null) {
                    node = (new BinTreeElement<>(tokens[i]));
                }
                else {
                    node = (new BinTreeElement<>(tokens[i]));
                }
            }
            //Setting the root
            else if (operators.contains(tokens[i])){
                root = new BinTreeElement<>(tokens[i]);
            }
            //Setting the tree to the original rootTree
            if (num > 0) {
                if (root.getLeft() == null) {
                    root.setLeft(node);
                } else {
                    root.setRight(node);
                }
            }
        }
        return root;
    }
    public static double evaluate(BinTreeElement<String> tree) {
        double answer = 0;
        double num1 = 0;
        double num2 = 0;
        String operator = "";
        //If tree leave isn't a number, it will do a recursive function
        if (!isNumeric(tree.getLeft().getValue())) {
            num1 = evaluate(tree.getLeft());
        }
        else {
            //Solves the problem in the tree leave and will return it
            //unless the tree leave it a single number
            num1 = Double.parseDouble(tree.getLeft().getValue());
            if (isNumeric(tree.getRight().getValue())) {
                num2 = Double.parseDouble(tree.getRight().getValue());
                operator = tree.getValue();
                switch (operator) {
                    case "+":
                        answer = num1 + num2;
                        break;
                    case "-":
                        answer = num1 - num2;
                        break;
                    case "*":
                        answer = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("Unable to divide by 0.");
                        }
                        answer = num1 / num2;
                        break;
                }
            }
            else {
                operator = tree.getValue();
                switch (operator) {
                    case "+":
                        answer = num1 + num2;
                        break;
                    case "-":
                        answer = num1 - num2;
                        break;
                    case "*":
                        answer = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("Unable to divide by 0.");
                        }
                        answer = num1 / num2;
                        break;
                }
            }
        }
        //If tree leave isn't number, will do recursive function
        if (!isNumeric(tree.getRight().getValue())) {
            num2 = evaluate(tree.getRight());
        }
        else {
            //Will solve tree leave or find single number and return it
            num2 = Double.parseDouble(tree.getRight().getValue());
            if (isNumeric(tree.getLeft().getValue())) {
                num1 = Double.parseDouble(tree.getLeft().getValue());
                operator = tree.getValue();
                switch (operator) {
                    case "+":
                        answer = num1 + num2;
                        break;
                    case "-":
                        answer = num1 - num2;
                        break;
                    case "*":
                        answer = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("Unable to divide by 0.");
                        }
                        answer = num1 / num2;
                        break;
                }
            }
            else {
                operator = tree.getValue();
                switch (operator) {
                    case "+":
                        answer = num1 + num2;
                        break;
                    case "-":
                        answer = num1 - num2;
                        break;
                    case "*":
                        answer = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("Unable to divide by 0.");
                        }
                        answer = num1 / num2;
                        break;
                }
            }
            return answer;
        }
        //Solves the entire rootTree
        operator = tree.getValue();
        switch (operator) {
            case "+":
                answer = num1 + num2;
                break;
            case "-":
                answer = num1 - num2;
                break;
            case "*":
                answer = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Unable to divide by 0.");
                }
                answer = num1 / num2;
                break;
        }
        return answer;
    }
    public static String getEquation(bridges.base.BinTreeElement<String> tree) {
        String equation = "";
        //Will go all the way to the left most end and get entire equation for left side of tree
        if (tree.getLeft() != null) {
            equation +=  "( " + getEquation(tree.getLeft()) + " ";
        }
        else {
            equation += tree.getValue();
            return equation;
        }
        //Getting root operator
        equation += tree.getValue();
        //Getting the entire right side of root tree
        if (tree.getRight() != null) {
            equation += " " + getEquation(tree.getRight());
        }
        else {
            equation += tree.getValue();
            return equation;
        }
        equation += " )";
        return equation;
    }
    public static void main(String[] args) throws Exception {
        Bridges bridges = new Bridges(5, "KyleVinod", "1261544500851");
        String expression = "( ( 7 + 3 ) * ( 5 - 2 ) )";
        BinTreeElement<String> parseTree = buildParseTree(expression);
        double d = evaluate(parseTree);
        String s = getEquation(parseTree);
        System.out.println(d);
        System.out.println(s);
        bridges.setDataStructure(parseTree);
        /* Visualize the Array */
        bridges.visualize();
    }
    //Determines if a string is a number
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
