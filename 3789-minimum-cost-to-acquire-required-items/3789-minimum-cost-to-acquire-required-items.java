
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth,
                            int need1, int need2) {

        long option1 = (long) need1 * cost1
                     + (long) need2 * cost2;

        long minNeed = Math.min(need1, need2);
        long maxNeed = Math.max(need1, need2);

        long option2 = minNeed * costBoth;

        if (need1 > need2) {
            option2 += (long) (need1 - need2) * cost1;
        } else {
            option2 += (long) (need2 - need1) * cost2;
        }

        long option3 = maxNeed * costBoth;

        return Math.min(option1,
               Math.min(option2, option3));
    }
}
