class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length+1][amount+1];
        int j =0;
        for( j =1; j <=amount; j++){
            dp[0][j] = Integer.MAX_VALUE;}

            for(int i =1; i <=coins.length; i++){
                for( j =1; j <= amount; j++)
                {if (coins[i-1] <= j && dp[i][j-coins[i-1]] != Integer.MAX_VALUE){
                    dp[i][j] = Math.min(1+ dp[i][j-coins[i-1]] , dp[i-1][j]);
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }}
            } 
        
        if(dp[coins.length][amount] == Integer.MAX_VALUE) return -1;
        else return dp[coins.length][amount];
        
    }
}