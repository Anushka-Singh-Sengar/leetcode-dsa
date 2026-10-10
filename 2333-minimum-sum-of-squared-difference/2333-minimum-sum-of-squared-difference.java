
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2,
                                 int k1, int k2) {

        int n = nums1.length;
        int[] freq = new int[100001];
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
            maxDiff = Math.max(maxDiff, diff);
        }

        long k = (long) k1 + k2;

        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (k >= freq[d]) {
                k -= freq[d];
                freq[d - 1] += freq[d];
                freq[d] = 0;
            } else {
                freq[d] -= (int) k;
                freq[d - 1] += (int) k;
                k = 0;
            }
        }

        long ans = 0;

        for (int d = 1; d <= maxDiff; d++) {
            ans += (long) d * d * freq[d];
        }

        return ans;
    }
}
