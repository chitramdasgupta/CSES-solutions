package org.example;

import java.util.Scanner;

public class RemovingDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int res = minSteps(n);
        System.out.println(res);
    }

    private static int minSteps(int n) {
        // dp[i]: min steps to reach 0 from value i
        int[] dp = new int[n + 1];

        dp[0] = 0; // Base case
        for (int i = 1; i <= n; i++) {
            int maxDigit = findMaxDigit(i);
            dp[i] = 1 + dp[i - maxDigit];
        }

        return dp[n];
    }

    private static int findMaxDigit(int n) {
        int res = 0;
        while (n > 0) {
            int digit = n % 10;
            res = Math.max(digit, res);
            n /= 10;
        }

        return res;
    }
}
