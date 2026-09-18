import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        // First and last occurrence of every character
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        // Store all valid intervals
        List<int[]> intervals = new ArrayList<>();

        for (int c = 0; c < 26; c++) {

            if (last[c] == -1) {
                continue; // character doesn't exist
            }

            int l = first[c];
            int r = last[c];

            boolean valid = true;

            int i = l;

            while (i <= r) {

                int current = s.charAt(i) - 'a';

                // This character appeared before l,
                // so we cannot make a valid substring starting at l.
                if (first[current] < l) {
                    valid = false;
                    break;
                }

                // We must include every occurrence of this character.
                r = Math.max(r, last[current]);

                i++;
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }

            return Integer.compare(a[0], b[0]);
        });

        List<String> ans = new ArrayList<>();

        int previousEnd = -1;

        for (int[] interval : intervals) {

            int l = interval[0];
            int r = interval[1];

            if (l > previousEnd) {
                ans.add(s.substring(l, r + 1));
                previousEnd = r;
            }
        }

        return ans;
    }
}