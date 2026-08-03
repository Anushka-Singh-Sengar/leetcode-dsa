class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int sum1 = nums[0];
         int sum2 =nums[0];
         int currentmax =nums[0];
          int currentmin =nums[0];
          int total =nums[0];
         for(int i = 1; i < nums.length; i++){
            currentmax = Math.max(nums[i], currentmax + nums[i]);
            sum1 = Math.max(sum1, currentmax);
            currentmin = Math.min(nums[i], currentmin + nums[i]);
            sum2 = Math.min(sum2, currentmin);
            total+= nums[i];

         }
         if (sum1 < 0) {
    return sum1;
}
         return Math.max(sum1, total-sum2);
        
    }}