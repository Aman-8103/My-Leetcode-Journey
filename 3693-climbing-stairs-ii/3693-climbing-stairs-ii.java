class Solution {
    public int climbStairs(int n, int[] costs) {
        int prev3 = 1_000_000_000;
        int prev2 = 1_000_000_000;
        int prev1 = 0;

        for (int i = 0; i < n; i++) {
            int curr = costs[i] + Math.min(prev1 + 1, Math.min(prev2 + 4, prev3 + 9));
            prev3 = prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
