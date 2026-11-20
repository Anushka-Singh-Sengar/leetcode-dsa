
import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1000000;

        // cost[i][j] = minimum changes to make s[i...j]
        // a semi-palindrome
        int[][] cost = new int[n][n];

        // Precompute costs for every substring
        for (int i = 0; i < n; i++) {
            for (int len = 2; i + len <= n; len++) {

                int minCost = INF;

                // Find all proper divisors of len
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Check each group for palindrome property
                    for (int r = 0; r < d; r++) {
                        int left = i + r;
                        int right = i + r +
                                ((len - 1 - r) / d) * d;

                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    minCost = Math.min(minCost, changes);
                }

                cost[i][i + len - 1] = minCost;
            }
        }

        // dp[p][i] = minimum changes to divide
        // first i characters into p substrings
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        // Partition the string into exactly k parts
        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) {

                // Previous partition ends at j
                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
