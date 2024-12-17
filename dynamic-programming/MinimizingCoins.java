package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class MinimizingCoins {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int amount = sc.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        int res = minNumCoins(amount, coins);
        System.out.println(res);
    }

    private static int minNumCoins(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }

                dp[i] = Math.min(1 + dp[i - coin], dp[i]);
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
