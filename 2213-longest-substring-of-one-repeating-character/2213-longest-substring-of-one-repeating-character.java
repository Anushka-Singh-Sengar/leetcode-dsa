class Solution {

    class Node {
        char leftChar;
        char rightChar;
        int prefix;
        int suffix;
        int best;
        int len;

        Node(char c) {
            leftChar = c;
            rightChar = c;
            prefix = 1;
            suffix = 1;
            best = 1;
            len = 1;
        }

        Node() {}
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        int n = s.length();
        int k = queryIndices.length;

        arr = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            arr[index] = ch;

            update(1, 0, n - 1, index);

            ans[i] = tree[1].best;
        }

        return ans;
    }

    // Build segment tree
    void build(int node, int start, int end) {

        if (start == end) {
            tree[node] = new Node(arr[start]);
            return;
        }

        int mid = (start + end) / 2;

        build(node * 2, start, mid);
        build(node * 2 + 1, mid + 1, end);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Update one index
    void update(int node, int start, int end, int index) {

        if (start == end) {
            tree[node] = new Node(arr[index]);
            return;
        }

        int mid = (start + end) / 2;

        if (index <= mid) {
            update(node * 2, start, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, end, index);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two segments
    Node merge(Node left, Node right) {

        Node res = new Node();

        res.len = left.len + right.len;

        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        // Initially, best is the best from either side
        res.best = Math.max(left.best, right.best);

        res.prefix = left.prefix;
        res.suffix = right.suffix;

        // If boundary characters are same,
        // suffix of left + prefix of right can join
        if (left.rightChar == right.leftChar) {

            res.best = Math.max(
                res.best,
                left.suffix + right.prefix
            );

            // Entire left segment has same character
            if (left.prefix == left.len) {
                res.prefix = left.len + right.prefix;
            }

            // Entire right segment has same character
            if (right.suffix == right.len) {
                res.suffix = right.len + left.suffix;
            }
        }

        return res;
    }
}.