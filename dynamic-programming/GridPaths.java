package org.example;

import java.util.Scanner;

public class GridPaths {
    private final static long MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            grid[i] = line.toCharArray();
        }

        int res = numGridPaths(grid);
        System.out.println(res);
    }

    private static int numGridPaths(char[][] grid) {
        if (grid[0][0] == '*') {
            return 0; // If the start is a trap, no paths exist
        }

        // dp[i][j]: number of paths from {i, j} to the lower-right cell
        int[][] dp = new int[grid.length][grid.length];

        // Base cases
        for (int i = grid.length - 1; i >= 0; i--) {
            if (grid[i][grid.length - 1] == '*') {
                break;
            }

            dp[i][grid.length - 1] = 1;
        }
        for (int i = grid.length - 1; i >= 0; i--) {
            if (grid[grid.length - 1][i] == '*') {
                break;
            }

            dp[grid.length - 1][i] = 1;
        }

        for (int i = grid.length - 2; i >= 0; i--) {
            for (int j = grid.length - 2; j >= 0; j--) {
                if (grid[i][j] != '*') {
                    dp[i][j] = (dp[i][j] + dp[i + 1][j] + dp[i][j + 1]) % 1000000007;
                }
            }
        }

        return dp[0][0];
    }
}
