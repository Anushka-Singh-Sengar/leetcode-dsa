class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int max =nums[0];
        int min = nums[0];
        int currentsum = nums[0];
        int currentmin = nums[0];
        for(int i =1; i <nums.length; i++){
            currentsum = Math.max(currentsum + nums[i] , nums[i]);
            max = Math.max(max, currentsum);
            currentmin = Math.min(currentmin+ nums[i], nums[i]);
            min = Math.min( currentmin, min);
        }
        if(Math.abs(min)> max){
            return Math.abs(min);}
            else{
                return max;
            }
        }
        
    }
