package org.example;

import java.util.Scanner;
import java.util.stream.Stream;

public class BookShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int total = sc.nextInt();

        sc.nextLine();
        int[] prices = Stream.of(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] pages = Stream.of(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int res = findMaxPages(total, prices, pages);
        System.out.println(res);
    }

    private static int findMaxPages(int total, int[] prices, int[] pages) {
        int[] dp = new int[total + 1]; // dp[i] is the max pages possible with a budget of i
        int res = 0;

        for (int i = 0; i < prices.length; ++i) {
            int price = prices[i];
            int page = pages[i];

            for (int j = total; j >= price; --j) {
                dp[j] = Math.max(dp[j - price] + page, dp[j]);

                res = Math.max(dp[j], res);
            }
        }

        return res;
    }
}
