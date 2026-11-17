import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        
        // 1. Precompute proper divisors for every possible substring length
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 1; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d < len; d++) {
                if (len % d == 0) {
                    divisors[len].add(d);
                }
            }
        }

        // 2. Precompute minimum changes needed to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = getSemiPalindromeCost(s, i, j, divisors[j - i + 1]);
            }
        }

        // 3. Dynamic Programming to partition into k semi-palindromes
        // dp[i][parts] = min changes for prefix s[0..i-1] split into 'parts' substrings
        int INF = 1_000_000;
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {
            for (int i = 2 * parts; i <= n; i++) {
                // Each semi-palindrome must have length >= 2 (since 1 <= d < len)
                for (int prev = 2 * (parts - 1); prev <= i - 2; prev++) {
                    dp[i][parts] = Math.min(dp[i][parts], dp[prev][parts - 1] + cost[prev][i - 1]);
                }
            }
        }

        return dp[n][k];
    }

    private int getSemiPalindromeCost(String s, int left, int right, List<Integer> divs) {
        int len = right - left + 1;
        int minChanges = Integer.MAX_VALUE;

        for (int d : divs) {
            int changes = 0;
            // Check each modulo group (0 to d - 1)
            for (int offset = 0; offset < d; offset++) {
                int l = left + offset;
                int r = right - (d - 1 - offset);
                while (l < r) {
                    if (s.charAt(l) != s.charAt(r)) {
                        changes++;
                    }
                    l += d;
                    r -= d;
                }
            }
            minChanges = Math.min(minChanges, changes);
        }

        return minChanges;
    }
}
