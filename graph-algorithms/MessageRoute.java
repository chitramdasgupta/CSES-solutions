import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static int numNodes;
    private static int numEdges;
    private static Map<Integer, List<Integer>> adjList;
    private static List<Integer> path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        numNodes = Integer.parseInt(split[0]);
        numEdges = Integer.parseInt(split[1]);

        adjList = new HashMap<>();
        for (int i = 1; i <= numNodes; i++) {
            adjList.put(i, new ArrayList<>());
        }
        for (int i = 0; i < numEdges; i++) {
            String[] line = br.readLine().split(" ");
            int node1 = Integer.parseInt(line[0]);
            int node2 = Integer.parseInt(line[1]);

            adjList.get(node1).add(node2);
            adjList.get(node2).add(node1);
        }

        if (bfs(1)) {
            System.out.println(path.size());
            System.out.println(String.join(" ", path.stream().map(String::valueOf).toArray(String[]::new)));
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }

    private static boolean bfs(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);

        int[] parents = new int[numNodes + 1];
        Arrays.fill(parents, -1);

        boolean[] visited = new boolean[numNodes + 1];
        visited[node] = true;

        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                int curr = q.poll();

                if (curr == numNodes) {
                    reconstructPath(parents, curr);
                    return true;
                }

                for (int neigh : adjList.get(curr)) {
                    if (visited[neigh]) {
                        continue;
                    }

                    q.add(neigh);
                    parents[neigh] = curr;
                    visited[neigh] = true;
                }
            }
        }

        return false;
    }

    private static void reconstructPath(int[] parents, int end) {
        List<Integer> temp = new ArrayList<>();
        Integer curr = end;

        while (curr != -1) {
            temp.add(curr);
            curr = parents[curr];
        }

        Collections.reverse(temp);
        path = temp;
    }
}
