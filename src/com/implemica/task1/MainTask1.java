package com.implemica.task1;

import java.util.*;

/**
 * Task 1
 * Class which finding the number of correct parentheses expressions
 * @author Artem Dementiev
 */
public class MainTask1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=0;
        while(true){
            System.out.print("Please enter N (N>0): ");
            if(sc.hasNextInt()) { // returns true if an integer can be read from the input stream
                n = sc.nextInt(); // reads an integer from the input stream and store in a variable
                if(n>0){
                    System.out.println("Number of correct parentheses for n=" + n + ": " + findNumberOfCorrectParentheses(n));
                    break;
                }
            } else {
                sc.next(); // to avoid "/n" in stdin, what would cause an infinite loop
                System.out.println("The input value is invalid! Try again.");
            }
        }
        sc.close();
    }

    /**
     * Function for finding the number of correct parentheses expressions
     * @param s amount of opening / closing brackets
     * @return number of correct parentheses
     */
    public static int findNumberOfCorrectParentheses(int s){
        List<String> outputList = new ArrayList<>();
        backtrack(outputList, "", s, s, s);
//        System.out.println(outputList.toString());
        return outputList.size();
    }

    /**
     * Recursive function to find all correct combinations
     * @param outputList container for combinations
     * @param current current string
     * @param amountOfOpenBrackets amount Of Open Brackets
     * @param amountOfCloseBrackets amount Of Close Brackets
     * @param max amount of opening / closing brackets
     */
    public static void backtrack(List<String> outputList, String current, int amountOfOpenBrackets, int amountOfCloseBrackets, int max){
        //if the brackets are over, then the line is ready
        if(current.length()==max*2){
            outputList.add(current);
            return;
        }

        if(amountOfOpenBrackets <= amountOfCloseBrackets && amountOfOpenBrackets > 0){
            backtrack(outputList, current+'(', amountOfOpenBrackets-1, amountOfCloseBrackets, max);
        }
        if(amountOfCloseBrackets > amountOfOpenBrackets && amountOfCloseBrackets > 0){
            backtrack(outputList, current+')', amountOfOpenBrackets, amountOfCloseBrackets-1, max);
        }

}
}
