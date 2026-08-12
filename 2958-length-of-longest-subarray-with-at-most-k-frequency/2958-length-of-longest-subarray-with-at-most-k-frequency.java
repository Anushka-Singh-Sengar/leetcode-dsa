class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        int left = 0;
        int maxLen = 0;
        
        for (int right = 0; right < nums.length; right++) {
            
            // Add nums[right] to the window
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            
            // If frequency becomes greater than k,
            // shrink the window from the left
            while (map.get(nums[right]) > k) {
                
                map.put(nums[left], map.get(nums[left]) - 1);
                left++;
            }
            
            // Current window [left ... right] is valid
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
