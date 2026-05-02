class Solution {
    public int maxProfit(int[] prices) {
        
        int currentsum =0;
        int maxsum =0;
        for (int i = 1; i< prices.length; i++){
            int diff = prices[i] - prices[i-1];
            currentsum = Math.max(0, currentsum + diff);
            maxsum = Math.max(maxsum, currentsum);
        }
        return maxsum;
    }
}