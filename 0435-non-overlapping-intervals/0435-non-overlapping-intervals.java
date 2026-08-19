class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int end = intervals[0][1];
        int count = 0;

        for (int i = 1; i < intervals.length; i++) {

            if (end > intervals[i][0]) {

                // Overlap
                count++;

                // Keep the interval ending earlier
                if (end > intervals[i][1]) {
                    end = intervals[i][1];
                }

            } else {

                // No overlap
                end = intervals[i][1];
            }
        }

        return count;
    }
}