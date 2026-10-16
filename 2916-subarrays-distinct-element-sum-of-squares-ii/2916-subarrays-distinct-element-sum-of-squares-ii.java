import java.util.Arrays;

public class Solution {
    private static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        
        // Statically sized flat arrays for optimal CPU cache performance
        long[] sum = new long[4 * n];
        long[] sumSquares = new long[4 * n];
        long[] lazy = new long[4 * n];

        // Find the maximum value to size the lastSeen tracking array precisely
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
            }
        }
        
        int[] lastSeen = new int[maxVal + 1];
        Arrays.fill(lastSeen, -1);

        long runningAns = 0;

        for (int r = 0; r < n; r++) {
            int val = nums[r];
            int l = lastSeen[val] + 1;
            lastSeen[val] = r;

            // Update the range [l, r] in O(log N)
            update(1, 0, n - 1, l, r, 1, sum, sumSquares, lazy);
            
            // Accumulate the current result from the root node (index 1)
            runningAns = (runningAns + sumSquares[1]) % MOD;
        }

        return (int) runningAns;
    }

    private void apply(int node, int start, int end, long val, long[] sum, long[] sumSquares, long[] lazy) {
        long len = end - start + 1;
        
        // Expansion: (a + val)^2 = a^2 + 2*a*val + val^2 * len
        long doubleValSum = (2 * val % MOD) * sum[node] % MOD;
        long squareValLen = ((val * val) % MOD) * len % MOD;

        sumSquares[node] = (sumSquares[node] + doubleValSum + squareValLen) % MOD;
        sum[node] = (sum[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void push(int node, int start, int end, long[] sum, long[] sumSquares, long[] lazy) {
        if (lazy[node] != 0) {
            int mid = (start + end) >> 1;
            int leftChild = node << 1;
            int rightChild = leftChild | 1;
            long lz = lazy[node];

            apply(leftChild, start, mid, lz, sum, sumSquares, lazy);
            apply(rightChild, mid + 1, end, lz, sum, sumSquares, lazy);
            lazy[node] = 0;
        }
    }

    private void update(int node, int start, int end, int l, int r, long val, long[] sum, long[] sumSquares, long[] lazy) {
        if (l <= start && end <= r) {
            apply(node, start, end, val, sum, sumSquares, lazy);
            return;
        }
        
        push(node, start, end, sum, sumSquares, lazy);
        int mid = (start + end) >> 1;
        int leftChild = node << 1;
        int rightChild = leftChild | 1;

        if (l <= mid) {
            update(leftChild, start, mid, l, r, val, sum, sumSquares, lazy);
        }
        if (r > mid) {
            update(rightChild, mid + 1, end, l, r, val, sum, sumSquares, lazy);
        }

        sum[node] = (sum[leftChild] + sum[rightChild]) % MOD;
        sumSquares[node] = (sumSquares[leftChild] + sumSquares[rightChild]) % MOD;
    }
}
