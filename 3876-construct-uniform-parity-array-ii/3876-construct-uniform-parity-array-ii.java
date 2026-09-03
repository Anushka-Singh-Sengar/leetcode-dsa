class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;

        for (int x : nums1) {
            if (x % 2 == 0) {
                minEven = Math.min(minEven, x);
            } else {
                minOdd = Math.min(minOdd, x);
            }
        }

        // All elements already have the same parity
        if (minOdd == Integer.MAX_VALUE || minEven == Integer.MAX_VALUE) {
            return true;
        }

        // Mixed parity: make every element odd.
        return minEven > minOdd;
    }
}