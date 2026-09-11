class Solution {
    public int totalNumbers(int[] digits) {

        int[] freq = new int[10];

        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        for (int i = 1; i <= 9; i++) {          // hundreds: cannot be 0
            for (int j = 0; j <= 9; j++) {      // tens
                for (int k = 0; k <= 8; k += 2) { // units: must be even

                    if (freq[i] == 0) continue;

                    freq[i]--;

                    if (freq[j] == 0) {
                        freq[i]++;
                        continue;
                    }

                    freq[j]--;

                    if (freq[k] > 0) {
                        count++;
                    }

                    freq[j]++;
                    freq[i]++;
                }
            }
        }

        return count;
    }
}