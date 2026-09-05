class Solution {
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                TreeNode temp = q.poll();

                // First node of this level
                // because we are going Right -> Left
                if (i == 0) {
                    ans.add(temp.val);
                }

                // Right first
                if (temp.right != null) {
                    q.offer(temp.right);
                }

                // Left second
                if (temp.left != null) {
                    q.offer(temp.left);
                }
            }
        }

        return ans;
    }
}