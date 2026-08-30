class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        q.add(root);

        while (!q.isEmpty()) {

            int size = q.size();

            List<Integer> temp = new ArrayList<>();

            for (int i = 0; i < size; i++) {

                TreeNode t = q.poll();

                temp.add(t.val);

                if (t.left != null) {
                    q.add(t.left);
                }

                if (t.right != null) {
                    q.add(t.right);
                }
            }

            ans.add(temp);
        }

        return ans;
    }
}