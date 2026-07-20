class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int need ;
        for(int i =0; i <nums.length; i++){
            int first =i;
            need = target- nums[i];
            if(map.containsKey(need)){
              int second =  map.get(need);
              return new int[]{first, second};
            }
            else{
                map.put(nums[i] , i);
            }
             
            
        }
 return new int[]{};
        
    }
}