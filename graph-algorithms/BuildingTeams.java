import java.io.*;
import java.util.*;

/*
We need to check if the given graph is bipartite using 2 colors
*/
class BuildingTeams {
    private static int numNodes;
    private static int numEdges;
    private static Map<Integer, List<Integer>> adjList;
    private static int[] parts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        numNodes = Integer.parseInt(split[0]);
        numEdges = Integer.parseInt(split[1]);

        adjList = new HashMap<>();
        for (int i = 1; i < numNodes + 1; ++i) {
            adjList.put(i, new ArrayList<>());
        }

        for (int i = 0; i < numEdges; ++i) {
            String[] line = br.readLine().split(" ");

            int u = Integer.parseInt(line[0]);
            int v = Integer.parseInt(line[1]);

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        parts = new int[numNodes + 1]; // can be 0 (unassigned), 1, or 2
        for (int i = 1; i < numNodes + 1; ++i) {
            if(parts[i] == 0) {
                if (!isBipartite(i)) {
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= numNodes; ++i) {
            result.append(parts[i]).append(" ");
        }
        System.out.println(result.toString().trim());
    }

    private static boolean isBipartite(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        parts[node] = 1; // The unvisited node is assigned 1 by default

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int neigh : adjList.get(curr)) {
                if (parts[neigh] == 0) {
                    parts[neigh] = 3 - parts[curr];
                    q.add(neigh);
                } else if (parts[neigh] == parts[curr]) {
                    // Conflict because the neighbor is already assigned the current team
                    return false;
                }
            }
        }

        return true;
    }
}
