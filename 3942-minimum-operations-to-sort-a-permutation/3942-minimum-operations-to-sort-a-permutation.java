
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int pos0 = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                pos0 = i;
                break;
            }
        }

        boolean forward = true;
        boolean backward = true;

        for (int i = 0; i < n; i++) {
            if (nums[(pos0 + i) % n] != i) {
                forward = false;
            }

            if (nums[(pos0 - i + n) % n] != i) {
                backward = false;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (forward) {
            ans = Math.min(ans, pos0);
            ans = Math.min(ans, n - pos0 + 2);
        }

        if (backward) {
            ans = Math.min(ans, pos0 + 2);
            ans = Math.min(ans, n - pos0);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
