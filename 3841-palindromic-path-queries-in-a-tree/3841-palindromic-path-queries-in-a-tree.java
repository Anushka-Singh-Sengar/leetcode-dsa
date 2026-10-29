
import java.util.*;

class Solution {
    int n, LOG, timer;
    List<Integer>[] graph;
    int[][] up;
    int[] depth, tin, tout, euler;
    char[] chars;
    FenwickXor bit;

    public List<Boolean> palindromePath(
        int n, int[][] edges, String s, String[] queries
    ) {
        this.n = n;
        chars = s.toCharArray();

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        euler = new int[n];

        timer = 0;
        buildTree();

        bit = new FenwickXor(n);

        for (int i = 0; i < n; i++) {
            int u = euler[i];
            bit.rangeXor(tin[u], tout[u], mask(chars[u]));
        }

        List<Boolean> results = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                int delta = mask(chars[u]) ^ mask(c);

                if (delta != 0) {
                    bit.rangeXor(tin[u], tout[u], delta);
                }

                chars[u] = c;
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int l = lca(u, v);

                int pathMask =
                    bit.pointQuery(tin[u])
                    ^ bit.pointQuery(tin[v])
                    ^ mask(chars[l]);

                results.add(
                    (pathMask & (pathMask - 1)) == 0
                );
            }
        }

        return results;
    }

    void buildTree() {
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int[] iter = new int[n];
        int[] stack = new int[n];

        int top = 0;
        stack[0] = 0;
        parent[0] = 0;

        while (top >= 0) {
            int u = stack[top];

            if (iter[u] == 0) {
                tin[u] = timer;
                euler[timer++] = u;

                up[0][u] = parent[u];

                for (int j = 1; j < LOG; j++) {
                    up[j][u] =
                        up[j - 1][up[j - 1][u]];
                }
            }

            if (iter[u] < graph[u].size()) {
                int v = graph[u].get(iter[u]++);

                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[++top] = v;
            } else {
                tout[u] = timer - 1;
                top--;
            }
        }
    }

    int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];

        for (int j = 0; j < LOG; j++) {
            if (((diff >> j) & 1) != 0) {
                a = up[j][a];
            }
        }

        if (a == b) {
            return a;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][a] != up[j][b]) {
                a = up[j][a];
                b = up[j][b];
            }
        }

        return up[0][a];
    }

    int mask(char c) {
        return 1 << (c - 'a');
    }

    static class FenwickXor {
        int[] tree;

        FenwickXor(int n) {
            tree = new int[n + 2];
        }

        void update(int i, int value) {
            for (i++; i < tree.length; i += i & -i) {
                tree[i] ^= value;
            }
        }

        int prefixXor(int i) {
            int result = 0;

            for (i++; i > 0; i -= i & -i) {
                result ^= tree[i];
            }

            return result;
        }

        void rangeXor(int left, int right, int value) {
            update(left, value);
            update(right + 1, value);
        }

        int pointQuery(int i) {
            return prefixXor(i);
        }
    }
}
