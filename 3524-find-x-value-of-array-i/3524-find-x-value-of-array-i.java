class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        
        // Use two pre-allocated long arrays to track large subarray combinations
        long[] dp = new long[k];
        long[] nextDp = new long[k];

        for (int num : nums) {
            int numMod = num % k;

            // Start a new subarray containing just the current element
            nextDp[numMod] = 1;

            // Extend all previous running remainders
            for (int i = 0; i < k; i++) {
                if (dp[i] > 0) { // Optimization: skip empty paths
                    int newMod = (i * numMod) % k;
                    nextDp[newMod] += dp[i];
                }
            }

            // Accumulate counts to our final long answer array
            for (int i = 0; i < k; i++) {
                ans[i] += nextDp[i];
            }

            // Swap references and clear nextDp for the next loop
            long[] temp = dp;
            dp = nextDp;
            nextDp = temp;
            java.util.Arrays.fill(nextDp, 0L); 
        }

        return ans;
    }
}
