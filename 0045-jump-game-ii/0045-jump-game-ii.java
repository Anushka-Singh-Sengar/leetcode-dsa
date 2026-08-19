class Solution {
    public int jump(int[] nums) {
        int far = 0;
        int l =0;
        int r =0;
        int jump =0;
        int i =0;
    while(r < nums.length-1){
        far =0;
        jump++;
        for( i =l; i <=r; i++){
            
            far = Math.max(nums[i] + i, far);
            if(far >= nums.length-1){
                return jump;
            }

        }
        l  = r+1;
        r = far;

    }
    return jump;
    }
}