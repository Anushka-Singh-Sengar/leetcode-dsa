
import java.util.*;

class Solution {

    class Fenwick {
        int n;
        long[] tree;

        Fenwick(int n) {
            this.n = n;
            tree = new long[n + 1];
        }

        void update(int i, long val) {
            while (i <= n) {
                tree[i] += val;
                i += i & -i;
            }
        }

        long query(int i) {
            long sum = 0;

            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }

            return sum;
        }

        // Find the smallest index whose prefix sum >= k
        int kth(int k) {
            int idx = 0;

            for (int bit = Integer.highestOneBit(n);
                 bit > 0; bit >>= 1) {

                int next = idx + bit;

                if (next <= n && tree[next] < k) {
                    idx = next;
                    k -= (int) tree[next];
                }
            }

            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int[] unique = Arrays.stream(sorted)
                .distinct()
                .toArray();

        int m = unique.length;

        Fenwick count = new Fenwick(m);
        Fenwick sum = new Fenwick(m);

        long totalSum = 0;

        // Calculate cost of every window of size x
        long[] cost = new long[n - x + 1];

        for (int i = 0; i < x; i++) {

            int id = Arrays.binarySearch(unique, nums[i]) + 1;

            count.update(id, 1);
            sum.update(id, nums[i]);

            totalSum += nums[i];
        }

        for (int i = 0; i <= n - x; i++) {

            // Find median (lower median)
            int medianId = count.kth((x + 1) / 2);

            long median = unique[medianId - 1];

            long leftCount = count.query(medianId);
            long leftSum = sum.query(medianId);

            long rightCount = x - leftCount;
            long rightSum = totalSum - leftSum;

            cost[i] =
                    median * leftCount - leftSum
                    + rightSum - median * rightCount;

            // Slide window
            if (i + x < n) {

                int removeId =
                        Arrays.binarySearch(unique, nums[i]) + 1;

                int addId =
                        Arrays.binarySearch(unique, nums[i + x]) + 1;

                count.update(removeId, -1);
                sum.update(removeId, -nums[i]);

                count.update(addId, 1);
                sum.update(addId, nums[i + x]);

                totalSum += (long) nums[i + x] - nums[i];
            }
        }

        // DP with space optimization
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        // Select exactly k non-overlapping windows
        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Don't select a window ending here
                curr[i] = curr[i - 1];

                // Select the last x elements
                if (i >= x && prev[i - x] != INF) {

                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}
