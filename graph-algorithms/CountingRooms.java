import java.util.*;

class CountingRooms {
    private static int numRows;
    private static int numCols;
    private static char[][] grid;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        numRows = sc.nextInt();
        numCols = sc.nextInt();

        grid = new char[numRows][numCols];

        for (int i = 0; i < numRows; ++i) {
            String line = sc.next();
            grid[i] = line.toCharArray();
        }

        int res = 0;
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                if (grid[i][j] == '.') {
                    ++res;
                    dfs(i, j);
                }
            }
        }

        System.out.println(res);
    }

    private static void dfs(int row, int col) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols ||
            grid[row][col] != '.') {
            return;
        }

        grid[row][col] = '#';

        dfs(row, col + 1);
        dfs(row + 1, col);
        dfs(row, col - 1);
        dfs(row - 1, col);
    }
}
