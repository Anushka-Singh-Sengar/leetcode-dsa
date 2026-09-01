class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        boolean flag = true;

        while (!q.isEmpty()) {

            int size = q.size();
            List<Integer> t = new ArrayList<>();

            for (int i = 0; i < size; i++) {

                TreeNode temp = q.poll();

                t.add(temp.val);

                if (temp.left != null) {
                    q.offer(temp.left);
                }

                if (temp.right != null) {
                    q.offer(temp.right);
                }
            }

            flag = !flag;

            if (flag) {
                Collections.reverse(t);
            }

            ans.add(t);
        }

        return ans;
    }
}