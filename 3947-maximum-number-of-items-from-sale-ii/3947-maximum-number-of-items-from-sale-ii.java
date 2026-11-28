
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        // Step 1: Find frequency of every factor
        int[] freq = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Step 2: Calculate number of eligible free item types
        int[] multiples = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                multiples[i] += freq[j];
            }
        }

        // Step 3: Sort items by price
        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) ->
            Integer.compare(items[a][1], items[b][1])
        );

        // Step 4: Greedily purchase beneficial items
        long remaining = budget;
        long freeEligiblePurchases = 0;

        for (int idx : order) {

            int factor = items[idx][0];
            int price = items[idx][1];

            // Number of distinct item types this item can unlock
            int limit = multiples[factor] - 1;

            // No additional benefit if limit is zero
            if (limit == 0) continue;

            // Premium purchases costing at least twice
            // the cheapest item price are unnecessary
            if (price >= 2L * minPrice) break;

            // Buy as many eligible copies as possible
            long count = Math.min(limit, remaining / price);

            freeEligiblePurchases += count;
            remaining -= count * price;
        }

        // Step 5: Remaining budget buys cheapest items
        long purchased = freeEligiblePurchases
                       + remaining / minPrice;

        // Each premium purchase also gives one free item
        long answer = purchased + freeEligiblePurchases;

        return (int) answer;
    }
}
