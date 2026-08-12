class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);

        boolean[] used = new boolean[nums.length];

        backtrack(nums, used, new ArrayList<>(), ans);

        return ans;
    }

    public void backtrack(
        int[] nums,
        boolean[] used,
        List<Integer> current,
        List<List<Integer>> ans
    ) {

        // permutation is complete
        if (current.size() == nums.length) {
            ans.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            // already used in current permutation
            if (used[i]) {
                continue;
            }

            // skip duplicate choices at the same level
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // choose
            used[i] = true;
            current.add(nums[i]);

            // explore
            backtrack(nums, used, current, ans);

            // undo
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}