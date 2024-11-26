import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int[] parents;
    static int[] ranks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        int numNodes = Integer.parseInt(split[0]);
        int numEdges = Integer.parseInt(split[1]);

        int[][] edges = new int[numEdges][2];
        for (int i = 0; i < numEdges; i++) {
            String[] line = br.readLine().split(" ");
            edges[i] = new int[]{Integer.parseInt(line[0]), Integer.parseInt(line[1])};
        }

        parents = new int[numNodes + 1];
        ranks = new int[numNodes + 1];
        for (int i = 0; i < numNodes + 1; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }

        for(int[] edge : edges) {
            merge(edge[0], edge[1]);
        }

        for (int i = 1; i <= numNodes; i++) {
            parents[i] = doFind(i); // Path compression to set each node to its farthest ancestor
        }

        Set<Integer> representatives = new HashSet<>();
        for (int parent: parents) {
            if (parent == 0) {
                continue; // The nodes are numbered 1 through n
            }
            representatives.add(parent);
        }
        List<Integer> representativesList = new ArrayList<>(representatives);

        List<int[]> edgesToAdd = new ArrayList<>();
        for (int i = 0; i < representatives.size() - 1; i++) {
            edgesToAdd.add(new int[] {representativesList.get(i), representativesList.get(i + 1)});
        }

        System.out.println(edgesToAdd.size());
        for (int[] edge : edgesToAdd) {
            System.out.println(edge[0] + " " + edge[1]);
        }
    }

    private static boolean merge(int first, int second) {
        int p1 = doFind(first);
        int p2 = doFind(second);

        if (p1 == p2) {
            return false;
        }

        if (ranks[p1] < ranks[p2]) {
            ranks[p2] += ranks[p1];
            parents[p1] = p2;
        } else {
            ranks[p1] += ranks[p2];
            parents[p2] = p1;
        }

        return true;
    }

    private static int doFind(int node) {
        int parent = parents[node];

        while (parent != parents[parent]) {
            parents[parent] = parents[parents[parent]];
            parent = parents[parent];
        }

        return parent;
    }
}
