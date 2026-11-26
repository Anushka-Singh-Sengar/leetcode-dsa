
import java.util.*;

class Solution {

    private void countingSort(int[] sa, int[] temp, int[] rank,
                              int offset, int maxRank) {
        int n = sa.length;
        int[] count = new int[Math.max(n + 2, maxRank + 2)];

        for (int idx : sa) {
            int key = (idx + offset < n) ? rank[idx + offset] : 0;
            count[key]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int idx = sa[i];
            int key = (idx + offset < n) ? rank[idx + offset] : 0;
            temp[--count[key]] = idx;
        }

        System.arraycopy(temp, 0, sa, 0, n);
    }

    private int[] buildSuffixArray(int[] nums) {
        int n = nums.length;

        int[] sa = new int[n];
        int[] rank = nums.clone();
        int[] temp = new int[n];
        int[] newRank = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
        }

        int maxRank = 100000;

        for (int k = 1; k < n; k *= 2) {

            countingSort(sa, temp, rank, k, maxRank);
            countingSort(sa, temp, rank, 0, maxRank);

            int classes = 1;
            newRank[sa[0]] = classes;

            for (int i = 1; i < n; i++) {
                int prev = sa[i - 1];
                int curr = sa[i];

                int prevSecond = (prev + k < n) ? rank[prev + k] : 0;
                int currSecond = (curr + k < n) ? rank[curr + k] : 0;

                if (rank[prev] != rank[curr] ||
                    prevSecond != currSecond) {
                    classes++;
                }

                newRank[curr] = classes;
            }

            int[] swap = rank;
            rank = newRank;
            newRank = swap;

            maxRank = classes;

            if (classes == n) break;
        }

        return sa;
    }

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] sa = buildSuffixArray(nums);

        // Find the rank of each suffix.
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[sa[i]] = i;
        }

        // Kasai's algorithm to calculate LCP.
        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            if (r == 0) {
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) h--;
        }

        // Find the minimum unique subarray length.
        int ans = n;

        for (int r = 0; r < n; r++) {
            int idx = sa[r];

            int longest = 0;

            if (r > 0) {
                longest = Math.max(longest, lcp[r]);
            }

            if (r < n - 1) {
                longest = Math.max(longest, lcp[r + 1]);
            }

            int len = longest + 1;

            if (len <= n - idx) {
                ans = Math.min(ans, len);
            }
        }

        return ans;
    }
}
