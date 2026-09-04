/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 class tuple{
    TreeNode node;
    int col;
    int row;
   public tuple(TreeNode node, int col, int row){
    this.node = node;
    this.col = col;
    this.row = row;
}
 }
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Queue<tuple> q = new LinkedList<tuple>();
        TreeMap<Integer , TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;

        }
        q.offer(new tuple(root , 0 , 0));
        while(!q.isEmpty()){
            
            int size = q.size();
            for(int i = 0; i < size; i++){
                tuple t= q.poll();
                TreeNode temp = t.node;
                int c = t.col;
                int r = t.row;
                if(temp.left != null){
                    q.offer(new tuple(temp.left, c-1, r+1 ));
                }
                 if(temp.right != null){
                    q.offer(new tuple(temp.right, c+1, r+1));
                }
                if(!map.containsKey(c)){
                    map.put(c, new TreeMap<>());
                }
                if(!map.get(c).containsKey(r)){
                    map.get(c).put(r, new PriorityQueue<>());
                }
                map.get(c).get(r).offer(temp.val);
            }

            

        }
        for(TreeMap<Integer, PriorityQueue<Integer>> row : map.values()){
            List<Integer> ls = new ArrayList<>();
            for(PriorityQueue<Integer> pq : row.values()){
                while(!pq.isEmpty()){
                    ls.add(pq.poll());
                }
                
            }
            ans.add(ls);

        }

    return ans;    
    }
}