class Solution {
    public boolean sumGame(String num) {

        int n = num.length();
        int half = n / 2;

        int sumLeft = 0;
        int sumRight = 0;

        int qLeft = 0;
        int qRight = 0;

        for (int i = 0; i < half; i++) {
            if (num.charAt(i) == '?') {
                qLeft++;
            } else {
                sumLeft += num.charAt(i) - '0';
            }
        }

        for (int i = half; i < n; i++) {
            if (num.charAt(i) == '?') {
                qRight++;
            } else {
                sumRight += num.charAt(i) - '0';
            }
        }

        int diff = sumLeft - sumRight;

        // Same number of ? on both sides
        if (qLeft == qRight) {
            return diff != 0;
        }

        // Odd difference in number of ?
        // Alice can always force inequality
        if ((qLeft - qRight) % 2 != 0) {
            return true;
        }

        int qDiff = Math.abs(qLeft - qRight);
        int target = (qDiff / 2) * 9;

        /*
         * Bob wins only when the existing sum difference
         * exactly balances the extra '?' side.
         */

        if (qLeft > qRight) {
            // Left has extra ?
            // Bob wins only if left is initially behind
            return diff != -target;
        } else {
            // Right has extra ?
            // Bob wins only if right is initially behind
            return diff != target;
        }
    }
}