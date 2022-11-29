/*
Kyle Vinod
Project 4
MyStack.java
This code implements the StackInterface
and enters the code for those methods to run.
The last method is then used to test if html
files are tag balanced correctly.
 */
package cmsc256;
import bridges.base.SLelement;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MyStack<E> implements StackInterface {
    //Instance Variables
    private SLelement<E> topNode;
    //Setting the default constructor
    public MyStack() {
        clear();
    }
    //The push method that adds newEntry to the top of the stack
    //if newEntry isn't null
    @Override
    public void push(Object newEntry) {
        if (newEntry == null) {
            throw new IllegalArgumentException();
        }
        SLelement newNode = new SLelement(newEntry, topNode);
        topNode = newNode;
    }
    //The pop method gets returns the element in the top of the stack
    //and removes that element from the stack
    // Will only work if stack is not empty
    @Override
    public Object pop() {
        E pop = null;
        if (isEmpty() == true) {
            throw new EmptyStackException();
        }
        else {
            assert (topNode != null);
            pop = topNode.getValue();
            topNode = topNode.getNext();
        }
        return pop;
    }
    //Returns the element that is on the top of the stack
    //and will only work is stack isn't empty
    @Override
    public Object peek() {
        if (isEmpty() == true) {
            throw new EmptyStackException();
        }
        return topNode.getValue();
    }
    //Determines if the stack is empty or not by
    //returning true for yes qnd false for no
    @Override
    public boolean isEmpty() {
        if (topNode == null) {
            return true;
        }
        else {
            return false;
        }
    }
    //Clears the stack
    @Override
    public void clear() {
        topNode = null;
    }
    //The method determines if the html file is tag balance properly
    public static boolean isBalanced(File webpage) throws FileNotFoundException {
        //Reading the file
        Scanner fileReader = new Scanner(webpage);
        String line = "";
        boolean balanced = false;
        //Adding each line on the file into the line variable
        while (fileReader.hasNextLine()) {
            line += fileReader.nextLine().trim();
        }
        //Creating a new stack
        Stack<String> stacker = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            String tester = "";
            //If the character equals this, then it assumes it will evaluate a tag
            if (line.charAt(i) == '<') {
                i++;
                //If that is true, then it will determine if it's a closing tag
                if (line.charAt(i) == '/') {
                    i++;
                    //If closing tag, then it will determine if the stack has anything
                    //and if not then it will return false
                    if (stacker.isEmpty() == true) {
                        return false;
                    }
                    //Adding the words in the tag into the tester variable
                    while (line.charAt(i) != '>') {
                        tester += line.charAt(i);
                        i++;
                    }
                    //If string pop out of stack equals the tester variable then it will return true
                    if (stacker.pop().equals(tester)) {
                        balanced = true;
                    }
                    //If not then the tags are not balanced properly and will return false
                    else {
                        return false;
                    }
                }
                //If it's not a closing tag, then it will have
                //the tester variable grab the word in the tag and
                //push it into the stack
                else {
                    while (line.charAt(i) != '>') {
                        tester += line.charAt(i);
                        i++;
                    }
                    stacker.push(tester);
                }
            }
        }
        return balanced;
    }
}
