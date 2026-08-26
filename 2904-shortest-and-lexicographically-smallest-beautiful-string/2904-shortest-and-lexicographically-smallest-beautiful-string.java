class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();

        int left = 0;
        int ones = 0;

        String ans = "";

        for (int right = 0; right < n; right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            // We have exactly k ones
            while (ones == k) {

                String current = s.substring(left, right + 1);

                // Update answer if:
                // 1. current is shorter
                // 2. same length but lexicographically smaller
                if (ans.equals("")
                        || current.length() < ans.length()
                        || (current.length() == ans.length()
                            && current.compareTo(ans) < 0)) {
                    ans = current;
                }

                // Move left forward to make substring shorter
                if (s.charAt(left) == '1') {
                    ones--;
                }

                left++;
            }
        }

        return ans;
    }
}