class Solution {
    public int maxProfit(int[] nums) {
        int buy = nums[0];
        int sell = 0;
        int  profit =  0;
        for(int i =1; i <nums.length; i++){
            sell = nums[i];
            if(sell>= buy){
                profit = Math.max(profit, sell-buy);


            }
            buy = Math.min(buy , nums[i]);
           
            }

return profit;
        }
        
    }
