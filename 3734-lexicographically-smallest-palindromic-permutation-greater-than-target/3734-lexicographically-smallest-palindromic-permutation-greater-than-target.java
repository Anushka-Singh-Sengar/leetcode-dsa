class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int halfLen = n / 2;

        // Count characters in s
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Check palindrome possibility
        int odd = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        // Characters available for the LEFT HALF
        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        String answer = null;

        // ------------------------------------------------
        // CASE 1:
        // Find a left half STRICTLY GREATER than target's
        // left half.
        // ------------------------------------------------

        for (int pivot = halfLen - 1; pivot >= 0; pivot--) {

            int[] remaining = halfFreq.clone();

            // Try to match target[0 ... pivot-1]
            boolean possible = true;

            for (int i = 0; i < pivot; i++) {

                int c = target.charAt(i) - 'a';

                if (remaining[c] == 0) {
                    possible = false;
                    break;
                }

                remaining[c]--;
            }

            if (!possible) {
                continue;
            }

            // At pivot, choose the smallest character
            // GREATER than target[pivot]
            int targetChar = target.charAt(pivot) - 'a';
            int bigger = -1;

            for (int c = targetChar + 1; c < 26; c++) {

                if (remaining[c] > 0) {
                    bigger = c;
                    break;
                }
            }

            if (bigger == -1) {
                continue;
            }

            remaining[bigger]--;

            // Build left half
            StringBuilder left = new StringBuilder();

            // Prefix same as target
            for (int i = 0; i < pivot; i++) {
                left.append(target.charAt(i));
            }

            // Bigger character at pivot
            left.append((char) ('a' + bigger));

            // Fill rest with smallest characters
            for (int c = 0; c < 26; c++) {
                while (remaining[c] > 0) {
                    left.append((char) ('a' + c));
                    remaining[c]--;
                }
            }

            String candidate = makePalindrome(left.toString(), middle, n);

            if (answer == null || candidate.compareTo(answer) < 0) {
                answer = candidate;
            }
        }

        // ------------------------------------------------
        // CASE 2:
        // Left half is EXACTLY equal to target's left half.
        // The complete palindrome may still be > target.
        // ------------------------------------------------

        int[] remaining = halfFreq.clone();
        boolean possible = true;

        for (int i = 0; i < halfLen; i++) {

            int c = target.charAt(i) - 'a';

            if (remaining[c] == 0) {
                possible = false;
                break;
            }

            remaining[c]--;
        }

        if (possible) {

            String left = target.substring(0, halfLen);

            String candidate = makePalindrome(left, middle, n);

            if (candidate.compareTo(target) > 0) {

                if (answer == null || candidate.compareTo(answer) < 0) {
                    answer = candidate;
                }
            }
        }

        return answer == null ? "" : answer;
    }


    private String makePalindrome(String left, int middle, int n) {

        StringBuilder result = new StringBuilder();

        result.append(left);

        // Middle character only for odd length
        if (n % 2 == 1) {
            result.append((char) ('a' + middle));
        }

        // Reverse of left half
        result.append(new StringBuilder(left).reverse());

        return result.toString();
    }
}