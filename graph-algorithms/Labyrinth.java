import java.io.*;
import java.util.*;

class Labyrinth {
    private static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final char[] moves = {'D', 'R', 'U', 'L'};
    private static int numRows, numCols;
    private static char[][] grid;
    private static String path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        numRows = Integer.parseInt(st.nextToken());
        numCols = Integer.parseInt(st.nextToken());

        grid = new char[numRows][numCols];
        Point start = null;

        for (int i = 0; i < numRows; ++i) {
            String line = br.readLine();
            grid[i] = line.toCharArray();
            for (int j = 0; j < numCols; ++j) {
                if (grid[i][j] == 'A') {
                    start = new Point(i, j);
                }
            }
        }

        boolean found = bfs(start);

        if (found) {
            System.out.println("YES");
            System.out.println(path.length());
            System.out.println(path);
        } else {
            System.out.println("NO");
        }
    }

    private static boolean bfs(Point start) {
        Queue<Point> q = new LinkedList<>();
        q.add(start);

        boolean[][] visited = new boolean[numRows][numCols];
        visited[start.x][start.y] = true;

        Map<Point, Point> parent = new HashMap<>(); // Point -> Parent point where the key was reached from
        Map<Point, Character> directionMap = new HashMap<>(); // Point -> the direction taken to reach the key point from the parent
        parent.put(start, null);

        while (!q.isEmpty()) {
            Point current = q.poll();

            if (grid[current.x][current.y] == 'B') {
                reconstructPath(parent, directionMap, current);
                return true;
            }

            for (int d = 0; d < directions.length; d++) {
                int newRow = current.x + directions[d][0];
                int newCol = current.y + directions[d][1];

                if (newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols
                        || grid[newRow][newCol] == '#' || visited[newRow][newCol]) {
                    continue;
                }

                Point next = new Point(newRow, newCol);

                q.add(next);
                visited[newRow][newCol] = true;

                parent.put(next, current);
                directionMap.put(next, moves[d]);
            }
        }

        return false;
    }

    private static void reconstructPath(Map<Point, Point> parent, Map<Point, Character> directionMap, Point end) {
        StringBuilder sb = new StringBuilder();
        Point current = end;

        while (parent.get(current) != null) {
            sb.append(directionMap.get(current));
            current = parent.get(current);
        }

        path = sb.reverse().toString();
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

// The optimized approach:
/*
import java.io.*;
import java.util.*;

class Labyrinth {
    private static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final char[] moves = {'D', 'R', 'U', 'L'};
    private static int numRows, numCols;
    private static char[][] grid;
    private static String path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        numRows = Integer.parseInt(st.nextToken());
        numCols = Integer.parseInt(st.nextToken());

        grid = new char[numRows][numCols];
        int startX = 0, startY = 0;

        for (int i = 0; i < numRows; i++) {
            String line = br.readLine();
            grid[i] = line.toCharArray();
            for (int j = 0; j < numCols; j++) {
                if (grid[i][j] == 'A') {
                    startX = i;
                    startY = j;
                }
            }
        }

        boolean found = bfs(startX, startY);

        if (found) {
            System.out.println("YES");
            System.out.println(path.length());
            System.out.println(path);
        } else {
            System.out.println("NO");
        }
    }

    private static boolean bfs(int startX, int startY) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY});

        boolean[][] visited = new boolean[numRows][numCols];
        visited[startX][startY] = true;

        int[][] parent = new int[numRows][numCols];
        char[][] direction = new char[numRows][numCols];

        for (int[] row : parent) Arrays.fill(row, -1);

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0], y = current[1];

            if (grid[x][y] == 'B') {
                reconstructPath(parent, direction, x, y, startX, startY);
                return true;
            }

            for (int d = 0; d < directions.length; d++) {
                int newRow = x + directions[d][0];
                int newCol = y + directions[d][1];

                if (newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols
                        || grid[newRow][newCol] == '#' || visited[newRow][newCol]) {
                    continue;
                }

                q.add(new int[]{newRow, newCol});
                visited[newRow][newCol] = true;

                parent[newRow][newCol] = x * numCols + y; // Flattened index
                direction[newRow][newCol] = moves[d];
            }
        }

        return false;
    }

    private static void reconstructPath(int[][] parent, char[][] direction, int x, int y, int startX, int startY) {
        StringBuilder sb = new StringBuilder();

        while (x != startX || y != startY) {
            sb.append(direction[x][y]);
            int parentIndex = parent[x][y];
            x = parentIndex / numCols;
            y = parentIndex % numCols;
        }

        path = sb.reverse().toString();
    }
}
*/
