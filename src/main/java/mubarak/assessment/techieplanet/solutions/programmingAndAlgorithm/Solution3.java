package mubarak.assessment.techieplanet.solutions.programmingAndAlgorithm;

import java.util.Scanner;

public class Solution3 {
    public static int sumDigits(long n) {
        if (n == 0) {
            return 0;
        }
        return (int) (n % 10) + sumDigits(n / 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input any digit");
        int input = scanner.nextInt();
        scanner.close();
        System.out.println("Output :::::: "+sumDigits(input));
    }
}
