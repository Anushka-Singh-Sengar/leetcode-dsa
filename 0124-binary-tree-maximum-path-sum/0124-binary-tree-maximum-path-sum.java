class Solution {
    int sum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return sum;
    }

    public int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));

        // Complete path can use BOTH sides
        sum = Math.max(sum, left + right + root.val);

        // Path returned to parent can use only ONE side
        return Math.max(left, right) + root.val;
    }
}