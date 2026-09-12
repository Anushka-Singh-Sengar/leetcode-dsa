
class Solution {

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // [left, right, weight, originalIndex]
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by starting point
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        // next[i] = first interval whose left > arr[i][1]
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {

            int left = i + 1;
            int right = n;

            while (left < right) {

                int mid = left + (right - left) / 2;

                if (arr[mid][0] > arr[i][1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            next[i] = left;
        }

        /*
         * dp[i][k] = best answer starting from i
         *            when we can still choose at most k intervals.
         */
        State[][] dp = new State[n + 1][5];

        // IMPORTANT:
        // When i == n, there are no intervals left.
        // The answer is empty regardless of k.
        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // Option 1: skip current interval
                State skip = dp[i + 1][k];

                // Option 2: take current interval
                State nextState = dp[next[i]][k - 1];

                List<Integer> list =
                    new ArrayList<>(nextState.indices);

                list.add(arr[i][3]);

                // Result must be lexicographically compared
                Collections.sort(list);

                State take = new State(
                    arr[i][2] + nextState.score,
                    list
                );

                dp[i][k] = better(take, skip) ? take : skip;
            }

            // If k = 0, we cannot take anything.
            dp[i][0] = new State(0, new ArrayList<>());
        }

        List<Integer> answer = dp[0][4].indices;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private boolean better(State a, State b) {

        // Larger score is better
        if (a.score != b.score) {
            return a.score > b.score;
        }

        // Same score -> lexicographically smaller indices
        for (int i = 0; i < Math.min(
                a.indices.size(),
                b.indices.size()
        ); i++) {

            if (!a.indices.get(i).equals(b.indices.get(i))) {
                return a.indices.get(i) < b.indices.get(i);
            }
        }

        // If one is a prefix of the other,
        // the shorter one is lexicographically smaller.
        return a.indices.size() < b.indices.size();
    }
}