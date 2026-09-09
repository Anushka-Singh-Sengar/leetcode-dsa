class Solution {
    public long countCommas(long n) {
        long ans = 0;

        // 1 comma: 1,000 and above
        if (n >= 1_000) {
            ans += n - 999;
        }

        // 2 commas: 1,000,000 and above
        if (n >= 1_000_000) {
            ans += n - 999_999;
        }

        // 3 commas: 1,000,000,000 and above
        if (n >= 1_000_000_000) {
            ans += n - 999_999_999;
        }

        // 4 commas: 1,000,000,000,000 and above
        if (n >= 1_000_000_000_000L) {
            ans += n - 999_999_999_999L;
        }

        // 5 commas: 1,000,000,000,000,000 and above
        if (n >= 1_000_000_000_000_000L) {
            ans += n - 999_999_999_999_999L;
        }

        return ans;
    }
}