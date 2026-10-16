
class Solution {
    static final long MOD = 1_000_000_007L;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);
        int[] last = new int[100001];

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            // Every subarray ending at i has a distinct count.
            // If x appeared before, only starts after its previous
            // occurrence gain one new distinct element.
            int left = last[x]; // 0 means unseen; see note below

            st.update(left, i, 1);
            ans = (ans + st.querySumSquares()) % MOD;

            last[x] = i + 1;
        }

        return (int) ans;
    }

    static class SegmentTree {
        int n;
        long[] sum, sq, lazy;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            sq = new long[4 * n];
            lazy = new long[4 * n];
        }

        void apply(int node, int l, int r, long v) {
            long len = r - l + 1L;

            sq[node] = (sq[node] + 2 * v * sum[node]
                    + len * v % MOD * v) % MOD;
            sum[node] = (sum[node] + len * v) % MOD;
            lazy[node] = (lazy[node] + v) % MOD;
        }

        void push(int node, int l, int r) {
            if (lazy[node] == 0 || l == r) return;

            int mid = (l + r) / 2;
            apply(node * 2, l, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }

        void update(int node, int l, int r,
                    int ql, int qr, long v) {
            if (ql > r || qr < l) return;

            if (ql <= l && r <= qr) {
                apply(node, l, r, v);
                return;
            }

            push(node, l, r);
            int mid = (l + r) / 2;

            update(node * 2, l, mid, ql, qr, v);
            update(node * 2 + 1, mid + 1, r, ql, qr, v);

            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
            sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
        }

        void update(int l, int r, long v) {
            if (l <= r) update(1, 0, n - 1, l, r, v);
        }

        long querySumSquares() {
            return sq[1];
        }
    }
}
