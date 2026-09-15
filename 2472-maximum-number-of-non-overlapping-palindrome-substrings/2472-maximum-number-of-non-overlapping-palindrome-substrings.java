class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];

        // Step 1: Precompute all palindrome substrings
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 2 || isPalindrome[i + 1][j - 1]) {
                        isPalindrome[i][j] = true;
                    }
                }
            }
        }

        // Step 2: DP to find max non-overlapping palindromes of length >= k
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i]; // Carry forward previous max
            for (int j = i - k + 1; j >= 0; j--) {
                if (isPalindrome[j][i]) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[j] + 1);
                    break; // Optimization: taking the earliest valid start is optimal
                }
            }
        }

        return dp[n];
    }
}
