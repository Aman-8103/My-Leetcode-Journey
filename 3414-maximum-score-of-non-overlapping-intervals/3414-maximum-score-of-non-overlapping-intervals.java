import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        
        // 1. Flatten intervals to primitive array for speed
        // Each entry: [start, end, weight, original_index]
        int[][] items = new int[n][4];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            items[i][0] = interval.get(0);
            items[i][1] = interval.get(1);
            items[i][2] = interval.get(2);
            items[i][3] = i;
        }
        
        // 2. Sort by start time (Fast primitive comparator)
        Arrays.sort(items, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        
        // 3. Precompute next non-overlapping element index using fast binary search
        int[] nextIdx = new int[n];
        for (int i = 0; i < n; i++) {
            int targetEnd = items[i][1];
            int low = i + 1, high = n - 1;
            int ans = n;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (items[mid][0] > targetEnd) {
                    ans = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            nextIdx[i] = ans;
        }
        
        // dp[i][j] = maximum weight using at most 'j' intervals from suffix i..n-1
        long[][] dp = new long[n + 1][5];
        
        // tracking[i][j] = an encoded 64-bit integer representing selected indices:
        // Lower bits contain sorted indices pack, count of elements inside, etc. 
        // For absolute speed and lexicographical correctness, we store lists explicitly inside an array
        int[][][] selectedIndices = new int[n + 1][5][];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 4; j++) {
                selectedIndices[i][j] = new int[0];
            }
        }
        
        // 4. Bottom-up Tabulation
        for (int i = n - 1; i >= 0; i--) {
            int currWeight = items[i][2];
            int originalIdx = items[i][3];
            int next = nextIdx[i];
            
            for (int j = 1; j <= 4; j++) {
                // Option A: Skip current interval
                long weightSkip = dp[i + 1][j];
                int[] indicesSkip = selectedIndices[i + 1][j];
                
                // Option B: Pick current interval
                long weightTake = currWeight + dp[next][j - 1];
                int[] nextIndices = selectedIndices[next][j - 1];
                
                // Construct temporary take array
                int[] indicesTake = new int[nextIndices.length + 1];
                indicesTake[0] = originalIdx;
                System.arraycopy(nextIndices, 0, indicesTake, 1, nextIndices.length);
                Arrays.sort(indicesTake); // Sort to ensure lexicographical comparisons work easily
                
                // Decision logic
                if (weightTake > weightSkip) {
                    dp[i][j] = weightTake;
                    selectedIndices[i][j] = indicesTake;
                } else if (weightSkip > weightTake) {
                    dp[i][j] = weightSkip;
                    selectedIndices[i][j] = indicesSkip;
                } else {
                    // Tie-breaker: Lexicographically smaller
                    if (isSmaller(indicesTake, indicesSkip)) {
                        dp[i][j] = weightTake;
                        selectedIndices[i][j] = indicesTake;
                    } else {
                        dp[i][j] = weightSkip;
                        selectedIndices[i][j] = indicesSkip;
                    }
                }
            }
        }
        
        return selectedIndices[0][4];
    }
    
    // Fast array-based lexicographical checker
    private boolean isSmaller(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);
        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }
        return a.length < b.length;
    }
}
