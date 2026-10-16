import java.util.Arrays;

public class Solution {
    private static final int MOD = 1_000_000_007;
    private long[] sum, sumSq, lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length, maxVal = 0;
        for (int x : nums) if (x > maxVal) maxVal = x;

        sum = new long[4 * n];
        sumSq = new long[4 * n];
        lazy = new long[4 * n];
        
        int[] lastSeen = new int[maxVal + 1];
        Arrays.fill(lastSeen, -1);

        long ans = 0;
        for (int r = 0; r < n; r++) {
            update(1, 0, n - 1, lastSeen[nums[r]] + 1, r, 1);
            lastSeen[nums[r]] = r;
            ans = (ans + sumSq[1]) % MOD;
        }
        return (int) ans;
    }

    private void update(int node, int start, int end, int l, int r, long val) {
        if (l <= start && end <= r) {
            long len = end - start + 1;
            sumSq[node] = (sumSq[node] + 2 * val * sum[node] + val * val % MOD * len) % MOD;
            sum[node] = (sum[node] + val * len) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
            return;
        }
        
        int mid = (start + end) >> 1, lc = node << 1, rc = lc | 1;
        if (lazy[node] != 0) {
            update(lc, start, mid, start, mid, lazy[node]);
            update(rc, mid + 1, end, mid + 1, end, lazy[node]);
            lazy[node] = 0;
        }
        
        if (l <= mid) update(lc, start, mid, l, r, val);
        if (r > mid) update(rc, mid + 1, end, l, r, val);
        
        sum[node] = (sum[lc] + sum[rc]) % MOD;
        sumSq[node] = (sumSq[lc] + sumSq[rc]) % MOD;
    }
}
