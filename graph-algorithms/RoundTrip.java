import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class RoundTrip {
    private static int numNodes;
    private static int numEdges;
    private static List<List<Integer>> adjList;
    private static List<Integer> path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        numNodes = Integer.parseInt(split[0]);
        numEdges = Integer.parseInt(split[1]);

        adjList = new ArrayList<>();
        for (int i = 0; i < numNodes + 1; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < numEdges; i++) {
            String[] line = br.readLine().split(" ");

            int u = Integer.parseInt(line[0]);
            int v = Integer.parseInt(line[1]);

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        path = new ArrayList<>();
        for (int i = 1; i < numNodes + 1; i++) {
            if (isCyclic(i, new HashSet<>())) {
                System.out.println(path.size());

                StringBuilder sb = new StringBuilder();
                for (Integer node : path) {
                    sb.append(node).append(" ");
                }

                System.out.println(sb.toString().trim());
                return;
            }
        }

        System.out.println("IMPOSSIBLE");
    }

    private static boolean isCyclic(int node, Set<Integer> curr) {
        if (curr.contains(node)) {
            path = new ArrayList<>(curr);
            return true;
        }

        curr.add(node);

        for (int neigh : adjList.get(node)) {
            if (isCyclic(neigh, curr)) {
                return true;
            }
        }

        curr.remove(node);

        return false;
    }
}
