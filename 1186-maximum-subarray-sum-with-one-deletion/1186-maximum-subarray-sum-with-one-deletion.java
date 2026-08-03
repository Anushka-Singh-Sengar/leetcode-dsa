class Solution {
    public int maximumSum(int[] nums) {
        int sum =nums[0];
        
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = 0;
        
        for(int i =1; i<nums.length; i++){
            dp[i][0] = Math.max(dp[i-1][0] + nums[i], nums[i]);
            dp[i][1] =Math.max( dp[i-1][1]+ nums[i] , dp[i-1][0] );
            sum = Math.max(sum , Math.max(dp[i][0], dp[i][1]));
           

        } 
        return sum;
    }
}