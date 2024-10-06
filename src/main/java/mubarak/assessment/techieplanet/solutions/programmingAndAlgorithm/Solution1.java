package mubarak.assessment.techieplanet.solutions.programmingAndAlgorithm;

import java.util.Scanner;

public class Solution1 {
    public static String numberToWords(int n) {
        String[] units = {
                "", "one", "two", "three", "four", "five", "six", "seven",
                "eight", "nine", "ten", "eleven", "twelve", "thirteen",
                "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
                "nineteen"
        };
        String[] tens = {
                "", "", "twenty", "thirty", "forty", "fifty"
        };

        if (n < 20) {
            return units[n];
        } else if (n < 60) {
            String ten = tens[n / 10];
            String unit = units[n % 10];
            if (unit.equals("")) {
                return ten;
            } else {
                return ten + "-" + unit;
            }
        } else {
            return "";
        }
    }

    public static String getHourInWords(int h) {
        int hour = h % 12;
        if (hour == 0) {
            return "twelve";
        } else {
            return numberToWords(hour);
        }
    }

    public static String timeInWords(int H, int M) {
        String result;
        if (M == 0 || M == 60) {
            int hour = H % 12 + (M == 60 ? 1 : 0);
            String hourInWords = getHourInWords(hour);
            result = hourInWords + " o'clock";
        } else if (M == 15) {
            String hourInWords = getHourInWords(H);
            result = "quarter past " + hourInWords;
        } else if (M == 30) {
            String hourInWords = getHourInWords(H);
            result = "half past " + hourInWords;
        } else if (M == 45) {
            int nextHour = H % 12 + 1;
            String hourInWords = getHourInWords(nextHour);
            result = "quarter to " + hourInWords;
        } else if (M < 30) {
            String minuteInWords = numberToWords(M);
            String hourInWords = getHourInWords(H);
            String minuteUnit = (M == 1) ? "minute" : "minutes";
            result = minuteInWords + " " + minuteUnit + " past " + hourInWords;
        } else {
            int minutesTo = 60 - M;
            String minuteInWords = numberToWords(minutesTo);
            int nextHour = H % 12 + 1;
            String hourInWords = getHourInWords(nextHour);
            String minuteUnit = (minutesTo == 1) ? "minute" : "minutes";
            result = minuteInWords + " " + minuteUnit + " to " + hourInWords;
        }
        return result.substring(0, 1).toUpperCase() + result.substring(1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input Hour in number");
        int H = scanner.nextInt();
        System.out.println("Please input Minute in number");
        int M = scanner.nextInt();
        scanner.close();

        System.out.println("Output :::::: "+timeInWords(H, M));
    }
}
