package com.implemica.task3;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Task 3
 * Find the sum of the digits in the number n! (i.e. n factorial)
 * @author Artem Dementiev
 */
public class MainTask3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input =0;
        while (true) {
            System.out.print("Please enter integer: ");
            if(sc.hasNextInt()) { // returns true if an integer can be read from the input stream
                input = sc.nextInt(); // reads an integer from the input stream and store in a variable
                System.out.println("Find the sum of the digits in the number: " +input);
                break;
            } else {
                sc.next(); // to avoid "/n" in stdin, what would cause an infinite loop
                System.out.println("The input value is invalid! Try again.");
            }
        }
        String digits = factorial(input).toString(); //turn a number into a string
        int sum=0;
        for(int i=0; i< digits.length(); i++){
            sum=sum+Integer.parseInt(String.valueOf(digits.charAt(i))); //summing up the digits
        }
        System.out.println("Sum of the digits in the number "+ input+"!: " + sum);
        sc.close();
    }

    /**
     * Since the result of the factorial is a very large number that can exceed the allowable range of int values and even long, we will use the type from the package
     * java.math - BigInteger to keep this immense value
     * @param n the factorial number of which you want to calculate
     * @return the factorial of a number
     */
    private static BigInteger factorial(int n)
    {
        BigInteger ret = BigInteger.ONE;
        for (int i = 1; i <= n; ++i) ret = ret.multiply(BigInteger.valueOf(i));
        return ret;
    }
}
