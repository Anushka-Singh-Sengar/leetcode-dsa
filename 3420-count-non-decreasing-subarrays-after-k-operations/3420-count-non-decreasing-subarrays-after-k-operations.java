
import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {

        long ans = 0;
        long cost = 0;

        // Each pair stores {value, count}
        Deque<int[]> dq = new ArrayDeque<>();

        int j = nums.length - 1;

        // Traverse from right to left
        for (int i = nums.length - 1; i >= 0; i--) {

            int num = nums[i];
            int count = 1;

            // Merge smaller values into the current value
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {

                int[] last = dq.pollLast();

                int value = last[0];
                int cnt = last[1];

                count += cnt;

                cost += (long) (num - value) * cnt;
            }

            dq.offerLast(new int[]{num, count});

            // Reduce window until cost becomes valid
            while (cost > k) {

                int[] first = dq.pollFirst();

                int value = first[0];
                int cnt = first[1];

                cost -= (long) (value - nums[j]);

                j--;

                if (cnt > 1) {
                    dq.offerFirst(new int[]{value, cnt - 1});
                }
            }

            // All subarrays ending between i and j are valid
            ans += j - i + 1L;
        }

        return ans;
    }
}
