class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;

        if (n == 1 || n % 2 == 0) return true;

        int[][] dp = new int[n][n];

        // Base case
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        // Fill DP diagonally
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;

                int left = nums[i] - dp[i + 1][j];
                int right = nums[j] - dp[i][j - 1];

                dp[i][j] = Math.max(left, right);
            }
        }

        return dp[0][n - 1] >= 0;
    }
}