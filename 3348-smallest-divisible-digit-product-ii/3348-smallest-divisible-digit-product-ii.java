class Solution {
    private int e2, e3, e5, e7, K;
    private int[][] df; // digit -> [exp2,exp3,exp5,exp7]
    private boolean[][][][][] feasible; // feasible[i][a][b][c][f]: can i digits (1-9) reduce state (a,b,c,f) to zero

    public String smallestNumber(String num, long t) {
        long tmp = t;
        while (tmp % 2 == 0) tmp /= 2;
        while (tmp % 3 == 0) tmp /= 3;
        while (tmp % 5 == 0) tmp /= 5;
        while (tmp % 7 == 0) tmp /= 7;
        if (tmp != 1) return "-1";

        long tt = t;
        while (tt % 2 == 0) { e2++; tt /= 2; }
        while (tt % 3 == 0) { e3++; tt /= 3; }
        while (tt % 5 == 0) { e5++; tt /= 5; }
        while (tt % 7 == 0) { e7++; tt /= 7; }
        K = e2 + e3 + e5 + e7;

        df = new int[10][4];
        for (int d = 1; d <= 9; d++) {
            int v = d, a = 0, b = 0, c = 0, f = 0;
            while (v % 2 == 0) { a++; v /= 2; }
            while (v % 3 == 0) { b++; v /= 3; }
            while (v % 5 == 0) { c++; v /= 5; }
            while (v % 7 == 0) { f++; v /= 7; }
            df[d][0] = a; df[d][1] = b; df[d][2] = c; df[d][3] = f;
        }

        buildFeasibleTable();

        char[] digits = num.toCharArray();
        int n = digits.length;

        // 1) find first zero index
        int z = n;
        for (int i = 0; i < n; i++) {
            if (digits[i] == '0') { z = i; break; }
        }
        int maxPos = Math.min(n - 1, z);
        int m = Math.min(n, z + 1); // number of prefix-states we need: prefixState[0..m]

        int[] pa = new int[m + 1], pb = new int[m + 1], pc = new int[m + 1], pf = new int[m + 1];
        pa[0] = e2; pb[0] = e3; pc[0] = e5; pf[0] = e7;
        for (int i = 1; i <= m; i++) {
            int d = digits[i - 1] - '0'; // guaranteed nonzero since i-1 < z
            pa[i] = Math.max(0, pa[i - 1] - df[d][0]);
            pb[i] = Math.max(0, pb[i - 1] - df[d][1]);
            pc[i] = Math.max(0, pc[i - 1] - df[d][2]);
            pf[i] = Math.max(0, pf[i - 1] - df[d][3]);
        }

        // 2) exact match check (only possible if num itself is zero-free, i.e. z == n)
        if (z == n) {
            if (pa[n] == 0 && pb[n] == 0 && pc[n] == 0 && pf[n] == 0) {
                return new String(digits);
            }
        }

        // 3) try deviating at the latest possible position first
        for (int pos = maxPos; pos >= 0; pos--) {
            int origDigit = digits[pos] - '0';
            int a0 = pa[pos], b0 = pb[pos], c0 = pc[pos], f0 = pf[pos];
            int remaining = n - pos - 1;
            for (int d = origDigit + 1; d <= 9; d++) {
                int a = Math.max(0, a0 - df[d][0]);
                int b = Math.max(0, b0 - df[d][1]);
                int c = Math.max(0, c0 - df[d][2]);
                int f = Math.max(0, f0 - df[d][3]);
                if (isFeasible(remaining, a, b, c, f)) {
                    char[] result = new char[n];
                    System.arraycopy(digits, 0, result, 0, pos);
                    result[pos] = (char) ('0' + d);
                    char[] suffix = fillSuffix(remaining, a, b, c, f);
                    System.arraycopy(suffix, 0, result, pos + 1, remaining);
                    return new String(result);
                }
            }
        }

        // 4) need a longer number: find minimal length m0 that can satisfy full requirement fresh
        int m0 = -1;
        for (int i = 0; i <= K; i++) {
            if (feasible[i][e2][e3][e5][e7]) { m0 = i; break; }
        }
        // m0 should always be found (<= K), since exponents only from digits 1-9 primes 2,3,5,7
        int L = Math.max(n + 1, m0);
        char[] suffix = fillSuffix(L, e2, e3, e5, e7);
        return new String(suffix);
    }

    private boolean isFeasible(int i, int a, int b, int c, int f) {
        if (i >= K) return true; // guaranteed: minimal digits needed <= a+b+c+f <= K
        return feasible[i][a][b][c][f];
    }

    private void buildFeasibleTable() {
        feasible = new boolean[K + 1][e2 + 1][e3 + 1][e5 + 1][e7 + 1];
        feasible[0][0][0][0][0] = true;
        for (int i = 1; i <= K; i++) {
            for (int a = 0; a <= e2; a++)
                for (int b = 0; b <= e3; b++)
                    for (int c = 0; c <= e5; c++)
                        for (int f = 0; f <= e7; f++) {
                            for (int d = 1; d <= 9; d++) {
                                int na = Math.max(0, a - df[d][0]);
                                int nb = Math.max(0, b - df[d][1]);
                                int nc = Math.max(0, c - df[d][2]);
                                int nf = Math.max(0, f - df[d][3]);
                                if (feasible[i - 1][na][nb][nc][nf]) {
                                    feasible[i][a][b][c][f] = true;
                                    break;
                                }
                            }
                        }
        }
    }

    // Build the lexicographically smallest suffix of length L that reduces state (a,b,c,f) to zero.
    // Assumes isFeasible(L,a,b,c,f) is true.
    private char[] fillSuffix(int L, int a, int b, int c, int f) {
        char[] result = new char[L];
        int tailLen, tailStart;
        if (L > K) {
            tailStart = L - K;
            tailLen = K;
            for (int i = 0; i < tailStart; i++) result[i] = '1';
        } else {
            tailStart = 0;
            tailLen = L;
        }

        int ca = a, cb = b, cc = c, cf = f;
        for (int i = tailLen; i >= 1; i--) {
            for (int d = 1; d <= 9; d++) {
                int na = Math.max(0, ca - df[d][0]);
                int nb = Math.max(0, cb - df[d][1]);
                int nc = Math.max(0, cc - df[d][2]);
                int nf = Math.max(0, cf - df[d][3]);
                if (feasible[i - 1][na][nb][nc][nf]) {
                    result[tailStart + (tailLen - i)] = (char) ('0' + d);
                    ca = na; cb = nb; cc = nc; cf = nf;
                    break;
                }
            }
        }
        return result;
    }
}