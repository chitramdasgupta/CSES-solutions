package org.example;

import java.util.Scanner;

public class DiceCombination {
    private static final long MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(numberOfDiceCombination(n));
    }

    private static int numberOfDiceCombination(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 1; // there is 1 way to get to sum 0, that is no way
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 6; ++j) {
                if (i - j < 0) {
                    continue;
                }

                dp[i] = (int) ((dp[i] + dp[i - j]) % MOD);
            }
        }

        return dp[n];
    }
}
