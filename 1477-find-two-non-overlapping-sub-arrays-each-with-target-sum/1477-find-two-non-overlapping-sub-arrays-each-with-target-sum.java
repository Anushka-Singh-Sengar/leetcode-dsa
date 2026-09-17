class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        
        int n = arr.length;
        int[] best = new int[n];
        
        int INF = Integer.MAX_VALUE / 2;
        int ans = INF;
        
        int left = 0;
        int sum = 0;
        int minLen = INF;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            
            // Shrink window if sum becomes greater than target
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            
            // Found a subarray with sum = target
            if (sum == target) {
                int len = right - left + 1;
                
                // Check for an earlier non-overlapping subarray
                if (left > 0 && best[left - 1] != INF) {
                    ans = Math.min(ans, len + best[left - 1]);
                }
                
                // Keep the shortest valid subarray seen so far
                minLen = Math.min(minLen, len);
            }
            
            // Best valid subarray up to this index
            if (right == 0) {
                best[right] = minLen;
            } else {
                best[right] = best[right - 1];
                if (minLen < best[right]) {
                    best[right] = minLen;
                }
            }
        }
        
        return ans == INF ? -1 : ans;
    }
}