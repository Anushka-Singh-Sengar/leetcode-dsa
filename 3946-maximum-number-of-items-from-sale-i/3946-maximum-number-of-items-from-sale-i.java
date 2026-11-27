import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        // Step 1: Calculate value of each item
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {

            int free = 0;

            for (int j = 0; j < n; j++) {

                if (i != j &&
                    items[j][0] % items[i][0] == 0) {
                    free++;
                }
            }

            value[i] = free + 1;
        }

        // Step 2: DP for items with higher prices
        int INF = -1000000000;

        int[] dpHigher = new int[budget + 1];
        Arrays.fill(dpHigher, INF);

        dpHigher[0] = 0;

        int answer = 0;

        // Step 3: Try every possible minimum price
        for (int p = 1500; p >= 1; p--) {

            int[] dpMin = new int[budget + 1];
            Arrays.fill(dpMin, INF);

            // Select items having price p
            for (int i = 0; i < n; i++) {

                if (items[i][1] != p) {
                    continue;
                }

                // 0/1 Knapsack
                for (int c = budget; c >= p; c--) {

                    // Select this item as the first
                    // item having minimum price p
                    if (dpHigher[c - p] != INF) {

                        dpMin[c] = Math.max(
                            dpMin[c],
                            dpHigher[c - p] + value[i]
                        );
                    }

                    // Add another item of price p
                    if (dpMin[c - p] != INF) {

                        dpMin[c] = Math.max(
                            dpMin[c],
                            dpMin[c - p] + value[i]
                        );
                    }
                }
            }

            // Step 4: Calculate maximum total items
            for (int c = 0; c <= budget; c++) {

                if (dpMin[c] != INF) {

                    int total = dpMin[c]
                        + (budget - c) / p;

                    answer = Math.max(answer, total);
                }
            }

            // Step 5: Add current-price items to dpHigher
            for (int i = 0; i < n; i++) {

                if (items[i][1] != p) {
                    continue;
                }

                for (int c = budget; c >= p; c--) {

                    if (dpHigher[c - p] != INF) {

                        dpHigher[c] = Math.max(
                            dpHigher[c],
                            dpHigher[c - p] + value[i]
                        );
                    }
                }
            }
        }

        return answer;
    }
}