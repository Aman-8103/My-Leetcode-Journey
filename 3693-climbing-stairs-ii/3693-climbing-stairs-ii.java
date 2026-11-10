import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            int stepCost = costs[i - 1];
            // Check valid previous steps: i-1, i-2, and i-3
            for (int j = Math.max(0, i - 3); j < i; j++) {
                int jumpCost = (i - j) * (i - j);
                dp[i] = Math.min(dp[i], dp[j] + stepCost + jumpCost);
            }
        }

        return dp[n];
    }
}
