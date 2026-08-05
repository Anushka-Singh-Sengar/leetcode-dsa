class Solution {

    public void dfs(int node, List<Integer>[] adj, boolean[] vis) {
        vis[node] = true;

        for (int next : adj[node]) {
            if (!vis[next]) {
                dfs(next, adj, vis);
            }
        }
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        // Build adjacency list
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : invocations) {
            adj[edge[0]].add(edge[1]);
        }

        // Mark suspicious methods
        boolean[] vis = new boolean[n];
        dfs(k, adj, vis);

        // Check if any outside method invokes a suspicious one
        for (int[] edge : invocations) {
            int u = edge[0];
            int v = edge[1];

            if (!vis[u] && vis[v]) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    ans.add(i);
                }
                return ans;
            }
        }

        // Return remaining methods
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                ans.add(i);
            }
        }

        return ans;
    }
}