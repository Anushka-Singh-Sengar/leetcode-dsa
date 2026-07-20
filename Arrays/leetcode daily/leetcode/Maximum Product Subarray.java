class Solution {
    public int maxProduct(int[] nums) {
        int minproduct =0;
        int maxproduct =0;
        int temp =0;
        int result=0;
        if(nums.length == 1){
            return nums[0];
        }
        for(int i =0; i< nums.length; i++)
        
{
    if(nums[i]<0){
        temp = minproduct;
        minproduct = maxproduct;
        maxproduct = temp;

    }
    minproduct = Math.min(nums[i], minproduct*nums[i]);
    maxproduct = Math.max(nums[i], maxproduct*nums[i]);
      result = Math.max(result, maxproduct);
}
 return result;     
    }
    
}