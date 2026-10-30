import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {

        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adj[u].add(new int[]{v, w});
        }

        int[][] dist = new int[n][k + 1];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );

        // {node, consecutiveCount, distance}
        dist[0][1] = 0;
        pq.offer(new int[]{0, 1, 0});

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();

            int u = curr[0];
            int cnt = curr[1];
            int d = curr[2];

            if (d != dist[u][cnt]) {
                continue;
            }

            if (u == n - 1) {
                return d;
            }

            for (int[] next : adj[u]) {

                int v = next[0];
                int w = next[1];

                int newCount;

                if (labels.charAt(u) == labels.charAt(v)) {
                    newCount = cnt + 1;
                } else {
                    newCount = 1;
                }

                if (newCount > k) {
                    continue;
                }

                int newDist = d + w;

                if (newDist < dist[v][newCount]) {

                    dist[v][newCount] = newDist;

                    pq.offer(new int[]{
                        v, newCount, newDist
                    });
                }
            }
        }

        return -1;
    }
}