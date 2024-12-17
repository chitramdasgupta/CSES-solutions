package org.example;

import java.util.Scanner;

// Permutation problem
public class CoinCombinationI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int amount = sc.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        int res = numCombinations(amount, coins);

        System.out.println(res);
    }

    private static int numCombinations(int amount, int[] coins) {
        int[] dp = new int[amount + 1];

        dp[0] = 1;
        for (int i = 1; i <= amount; i++) {
            for (int coin: coins) {
                if (i - coin < 0) {
                    continue;
                }

                dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
            }
        }

        return dp[amount];
    }
}
