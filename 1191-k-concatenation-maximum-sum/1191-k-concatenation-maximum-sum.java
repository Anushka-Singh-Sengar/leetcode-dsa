class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {

        long MOD = 1_000_000_007L;

        long totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        long current = 0;
        long best = 0;

        int limit = (k == 1) ? arr.length : arr.length * 2;

        for (int i = 0; i < limit; i++) {
            int num = arr[i % arr.length];
            current = Math.max((long) num, current + num);
            best = Math.max(best, current);
        }

        if (totalSum > 0 && k > 2) {
            best += (long) (k - 2) * totalSum;
        }

        return (int) (best % MOD);
    }
}