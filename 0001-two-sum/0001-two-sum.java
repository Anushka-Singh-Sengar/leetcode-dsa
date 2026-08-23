class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int key = nums[i];
            
           
            int rem= target - key;
            if(map.containsKey(rem)){
                return new int[]{map.get(rem), i};
            }
             if(!map.containsKey(key)){
                map.put(key , i);
            }
        }
        return new int[]{};
        
    }
}