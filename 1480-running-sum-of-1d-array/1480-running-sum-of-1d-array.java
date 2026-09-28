class Solution {
    public int[] runningSum(int[] nums) {
        int[] sum = new int[nums.length];
        int ans = 0;
        for(int i =0; i< nums.length; i++){

            sum[i]= ans + nums[i];
            ans+= nums[i];
        }
        return sum;

        






    }
}