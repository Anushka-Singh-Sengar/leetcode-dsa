class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Build mask for each row having reservations
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int s = seat[1];

            map.put(row, map.getOrDefault(row, 0) | (1 << s));
        }

        // Rows with no reservations can always fit 2 groups
        int ans = (n - map.size()) * 2;

        int left = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        int middle = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
        int right = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

        for (int mask : map.values()) {

            boolean canLeft = (mask & left) == 0;
            boolean canMiddle = (mask & middle) == 0;
            boolean canRight = (mask & right) == 0;

            if (canLeft && canRight) {
                ans += 2;
            } else if (canLeft || canMiddle || canRight) {
                ans += 1;
            }
        }

        return ans;
    }
}