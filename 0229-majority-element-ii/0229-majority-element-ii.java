class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;

        int c1 = 0;
        int c2 = 0;

    int count1=0;
    int count2 =0;
        for(int i =0; i < nums.length; i++){
       if(nums[i] == c1){
                count1++;
            }
        else if(nums[i] == c2){
                count2++;
            }
            else if(count1 ==0){
            c1 = nums[i];
            count1++;
        }
        else if(count2 == 0){
            c2 = nums[i];
            count2++;
        }
        else{
                count1--;
                count2--;
            }}
            count1 = 0;
count2 = 0;
            for (int num : nums) {
    if (num == c1) {
        count1++;
    } else if (num == c2) {
        count2++;
    }
}
            if(count1 > n/3){
                ans.add(c1);

            }
            if(count2 > n/3 && c1 != c2) {
                ans.add(c2);
            }
            return ans;
            
        
        
    }}
