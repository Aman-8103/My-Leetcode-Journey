class Solution {
    private static final int MOD = 1_000_000_007;
    private long[] fact;
    private long[] invFact;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Precompute factorials and modular inverse factorials for O(1) combinations
        fact = new long[n + 1];
        invFact = new long[n + 1];
        fact[0] = 1;
        invFact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        invFact[n] = power(fact[n], MOD - 2);
        for (int i = n - 1; i >= 1; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }

        // Use a primitive array container to prevent object allocation inside recursion
        int[] buffer = new int[n];
        System.arraycopy(nums, 0, buffer, 0, n);

        return (int) ((dfs(buffer, 0, n) - 1 + MOD) % MOD);
    }

    private long dfs(int[] nums, int start, int end) {
        if (end - start <= 2) {
            return 1;
        }

        int root = nums[start];
        int leftLen = 0, rightLen = 0;

        // Count sizes to know where to split in our continuous block
        for (int i = start + 1; i < end; i++) {
            if (nums[i] < root) leftLen++;
            else rightLen++;
        }

        // Allocate block partitions within a primitive stack layout
        int[] nextSubtree = new int[end - start - 1];
        int lIdx = 0;
        int rIdx = leftLen;

        for (int i = start + 1; i < end; i++) {
            if (nums[i] < root) {
                nextSubtree[lIdx++] = nums[i];
            } else {
                nextSubtree[rIdx++] = nums[i];
            }
        }

        // Recursive evaluations
        long leftWays = dfs(nextSubtree, 0, leftLen);
        long rightWays = dfs(nextSubtree, leftLen, leftLen + rightLen);

        // C(n, k) = fact[n] * invFact[k] * invFact[n-k]
        long combinations = fact[leftLen + rightLen];
        combinations = (combinations * invFact[leftLen]) % MOD;
        combinations = (combinations * invFact[rightLen]) % MOD;

        long totalWays = (combinations * leftWays) % MOD;
        return (totalWays * rightWays) % MOD;
    }

    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
}
