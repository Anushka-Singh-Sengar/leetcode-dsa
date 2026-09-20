class Solution {
    public int reverseDegree(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // a -> 26, b -> 25, ..., z -> 1
            int reversePosition = 'z' - ch + 1;

            // String position is 1-indexed
            int position = i + 1;

            ans += reversePosition * position;
        }

        return ans;
    }
}