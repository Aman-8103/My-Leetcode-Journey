import java.util.Arrays;

class Solution {
    // Digits 1-9 ke prime factors (2, 3, 5, 7) ka lookup matrix
    private static final int[][] DIGIT_FACTORS = new int[10][4];
    static {
        int[] primes = {2, 3, 5, 7};
        for (int d = 1; d <= 9; d++) {
            int temp = d;
            for (int pIdx = 0; pIdx < 4; pIdx++) {
                while (temp % primes[pIdx] == 0) {
                    DIGIT_FACTORS[d][pIdx]++;
                    temp /= primes[pIdx];
                }
            }
        }
    }

    public String smallestNumber(String num, long t) {
        int[] req = new int[4]; // [2, 3, 5, 7]
        long tempT = t;
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (tempT % primes[i] == 0) {
                req[i]++;
                tempT /= primes[i];
            }
        }
        if (tempT > 1) return "-1"; // Invalid factor present

        int n = num.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = num.charAt(i) - '0';
        }

        int[][] prefixReq = new int[n + 1][4];
        System.arraycopy(req, 0, prefixReq[0], 0, 4);

        int firstZero = -1;
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) {
                firstZero = i;
                break;
            }
            for (int pIdx = 0; pIdx < 4; pIdx++) {
                prefixReq[i + 1][pIdx] = Math.max(0, prefixReq[i][pIdx] - DIGIT_FACTORS[digits[i]][pIdx]);
            }
        }

        // Case 1: Original number itself is valid
        if (firstZero == -1 && prefixReq[n][0] == 0 && prefixReq[n][1] == 0 && prefixReq[n][2] == 0 && prefixReq[n][3] == 0) {
            return num;
        }

        // Case 2: Backtrack from rightmost available position
        int limit = (firstZero == -1) ? n - 1 : firstZero;
        for (int i = limit; i >= 0; i--) {
            for (int d = digits[i] + 1; d <= 9; d++) {
                int rem2 = Math.max(0, prefixReq[i][0] - DIGIT_FACTORS[d][0]);
                int rem3 = Math.max(0, prefixReq[i][1] - DIGIT_FACTORS[d][1]);
                int rem5 = Math.max(0, prefixReq[i][2] - DIGIT_FACTORS[d][2]);
                int rem7 = Math.max(0, prefixReq[i][3] - DIGIT_FACTORS[d][3]);

                int remLen = n - 1 - i;
                if (canForm(rem2, rem3, rem5, rem7, remLen)) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < i; j++) sb.append(digits[j]);
                    sb.append(d);
                    sb.append(fillGreedily(rem2, rem3, rem5, rem7, remLen));
                    return sb.toString();
                }
            }
        }

        // Case 3: Need a longer length representation
        int remLen = n + 1;
        while (true) {
            if (canForm(req[0], req[1], req[2], req[3], remLen)) {
                return fillGreedily(req[0], req[1], req[2], req[3], remLen);
            }
            remLen++;
        }
    }

    // O(1) loop to check all possible distributions of 2s and 3s perfectly
    private int[] getBestDigits(int r2, int r3) {
        int[] best = null;
        int bestCount = Integer.MAX_VALUE;
        
        for (int c9 = 0; c9 <= r3 / 2; c9++) {
            int maxC6 = Math.min(r3 - 2 * c9, r2);
            for (int c6 = 0; c6 <= maxC6; c6++) {
                int c3 = r3 - 2 * c9 - c6;
                int rem2 = r2 - c6;
                int c8 = rem2 / 3;
                int rem2Mod = rem2 % 3;
                int c4 = (rem2Mod == 2) ? 1 : 0;
                int c2 = (rem2Mod == 1) ? 1 : 0;
                
                int count = c2 + c3 + c4 + c6 + c8 + c9;
                int[] curr = new int[10];
                curr[2] = c2; curr[3] = c3; curr[4] = c4; 
                curr[6] = c6; curr[8] = c8; curr[9] = c9;
                
                if (best == null) {
                    best = curr;
                    bestCount = count;
                } else {
                    if (count != bestCount) {
                        if (count < bestCount) {
                            best = curr;
                            bestCount = count;
                        }
                    } else {
                        // Maximize smaller digits first for optimal lexicographical sort
                        for (int d = 2; d <= 9; d++) {
                            if (curr[d] != best[d]) {
                                if (curr[d] > best[d]) {
                                    best = curr;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        return best;
    }

    private boolean canForm(int r2, int r3, int r5, int r7, int len) {
        int[] best = getBestDigits(r2, r3);
        int totalDigits = r5 + r7;
        if (best != null) {
            for (int d = 2; d <= 9; d++) totalDigits += best[d];
        }
        return totalDigits <= len;
    }

    private String fillGreedily(int r2, int r3, int r5, int r7, int len) {
        int[] best = getBestDigits(r2, r3);
        best[5] = r5;
        best[7] = r7;
        
        StringBuilder sb = new StringBuilder();
        int nonOnes = 0;
        for (int d = 2; d <= 9; d++) nonOnes += best[d];
        
        int onesNeeded = len - nonOnes;
        for (int i = 0; i < onesNeeded; i++) {
            sb.append('1');
        }
        
        for (int d = 2; d <= 9; d++) {
            for (int i = 0; i < best[d]; i++) {
                sb.append(d);
            }
        }
        return sb.toString();
    }
}
