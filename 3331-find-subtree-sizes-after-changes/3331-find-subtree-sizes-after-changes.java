
class Solution {
    List<Integer>[] tree;
    int[] ans;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] original = new ArrayList[n];
        tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            original[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            original[parent[i]].add(i);
        }

        int[] last = new int[26];
        Arrays.fill(last, -1);

        // First DFS: construct modified tree
        buildTree(0, original, parent, s, last);

        // Second DFS: calculate subtree sizes
        ans = new int[n];
        calculateSize(0);

        return ans;
    }

    private void buildTree(int node, List<Integer>[] original,
                           int[] parent, String s, int[] last) {

        int c = s.charAt(node) - 'a';

        int previous = last[c];
        last[c] = node;

        for (int child : original[node]) {

            int newParent = last[s.charAt(child) - 'a'];

            if (newParent == -1) {
                newParent = node;
            }

            tree[newParent].add(child);

            buildTree(child, original, parent, s, last);
        }

        last[c] = previous;
    }

    private int calculateSize(int node) {
        int size = 1;

        for (int child : tree[node]) {
            size += calculateSize(child);
        }

        return ans[node] = size;
    }
}
