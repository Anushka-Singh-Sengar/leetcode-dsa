class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        
        int start =0;
        int total  =0;
        int n = gas.length;
        int tank =0;


        for(int i =0; i <n; i++){
            total+= gas[i] - cost[i];
            tank+= gas[i] - cost[i];
            if(tank <0){
                tank =0;
                start = i+1;
            }
        }
        if(total >=0) return start ;
        else return -1;

    }
}