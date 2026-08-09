class Solution {

    int[][] dp;
    int[] suffix;
    int n;

    public int stoneGameII(int[] piles) {

        n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n + 1];

        // suffix sum
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solve(0, 1);
    }

    int solve(int i, int M) {

        // All remaining stones can be taken
        if (i + 2 * M >= n) {
            return suffix[i];
        }

        if (dp[i][M] != -1) {
            return dp[i][M];
        }

        int ans = 0;

        for (int X = 1; X <= 2 * M; X++) {

            int opponent = solve(
                i + X,
                Math.max(M, X)
            );

            int current = suffix[i] - opponent;

            ans = Math.max(ans, current);
        }

        return dp[i][M] = ans;
    }
}