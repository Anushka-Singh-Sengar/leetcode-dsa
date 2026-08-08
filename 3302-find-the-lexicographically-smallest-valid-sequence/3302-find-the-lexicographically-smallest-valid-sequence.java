class Solution {

    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();

        // dp[i] = maximum suffix of word2
        // that can be matched exactly in word1[i...]
        int[] dp = new int[n + 1];

        int j = m - 1;

        // Build suffix DP
        for (int i = n - 1; i >= 0; i--) {

            if (j >= 0 && w1[i] == w2[j]) {
                dp[i] = dp[i + 1] + 1;
                j--;
            } else {
                dp[i] = dp[i + 1];
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        // Greedily find the earliest possible indices
        while (i < n && j < m) {

            // Case 1: exact match
            if (w1[i] == w2[j]) {

                ans[j] = i;
                j++;

            } 
            // Case 2: use our one allowed modification
            else if (dp[i + 1] >= m - j - 1) {

                ans[j] = i;
                j++;

                // We have now used the one mismatch.
                i++;

                break;
            }

            i++;
        }

        // We used mismatch and now match
        // everything else exactly.
        while (i < n && j < m) {

            if (w1[i] == w2[j]) {
                ans[j] = i;
                j++;
            }

            i++;
        }

        // Couldn't construct complete sequence
        if (j < m) {
            return new int[0];
        }

        return ans;
    }
}.