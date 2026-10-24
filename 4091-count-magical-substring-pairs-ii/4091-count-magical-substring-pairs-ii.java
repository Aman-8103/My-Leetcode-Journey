class Solution {
    public long countMagicalPairs(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        // leftCounts[j * 27 + d] stores how many i <= j have distinct(s[i..j]) == d
        int[] leftCounts = new int[(n - 1) * 27];

        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        int[] leftPos = new int[27];
        int leftLen = 0;

        // Pass 1: Left-to-right to compute distinct counts for s1 = s[i..j]
        for (int j = 0; j < n - 1; j++) {
            int c = chars[j] - 'a';
            int old = lastSeen[c];
            lastSeen[c] = j;

            int idx = leftLen;
            if (old == -1) {
                leftLen++;
                leftPos[leftLen] = -1;
            } else {
                for (int p = 0; p < leftLen; p++) {
                    if (leftPos[p] == old) {
                        idx = p;
                        break;
                    }
                }
            }

            for (int p = idx; p > 0; p--) {
                leftPos[p] = leftPos[p - 1];
            }
            leftPos[0] = j;

            int base = j * 27;
            for (int d = 1; d <= leftLen; d++) {
                leftCounts[base + d] = leftPos[d - 1] - leftPos[d];
            }
        }

        // Pass 2: Right-to-left to compute distinct counts for s2 = s[j + 1..k] and match
        int[] nextSeen = new int[26];
        Arrays.fill(nextSeen, -1);
        int[] rightPos = new int[27];
        int rightLen = 0;
        long totalPairs = 0;

        for (int j = n - 2; j >= 0; j--) {
            int pos = j + 1;
            int c = chars[pos] - 'a';
            int old = nextSeen[c];
            nextSeen[c] = pos;

            int idx = rightLen;
            if (old == -1) {
                rightLen++;
                rightPos[rightLen] = n;
            } else {
                for (int p = 0; p < rightLen; p++) {
                    if (rightPos[p] == old) {
                        idx = p;
                        break;
                    }
                }
            }

            for (int p = idx; p > 0; p--) {
                rightPos[p] = rightPos[p - 1];
            }
            rightPos[0] = pos;

            int base = j * 27;
            for (int d = 1; d <= rightLen; d++) {
                long leftWays = leftCounts[base + d];
                if (leftWays > 0) {
                    long rightWays = rightPos[d] - rightPos[d - 1];
                    totalPairs += leftWays * rightWays;
                }
            }
        }

        return totalPairs;
    }
}