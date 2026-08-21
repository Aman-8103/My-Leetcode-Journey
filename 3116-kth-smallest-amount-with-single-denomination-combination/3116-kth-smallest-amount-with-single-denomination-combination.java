import java.util.Arrays;

public class Solution {

    private static final int MAX_SUBSETS = 32768;
    private final long[] packedSubsets = new long[MAX_SUBSETS * 2];
    private int subsetCount = 0;

    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);
        
        long low = 1;
        long high = (long) coins[0] * k;
        
        // Reset global counter for clean LeetCode test suite isolation
        subsetCount = 0;
        
        // Generate pre-filtered items using primitives
        generateSubsets(coins, 0, 1, 0, high);
        
        long ans = high;
        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (countMultiples(mid) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private void generateSubsets(int[] coins, int index, long currentLcm, int count, long maxLimit) {
        if (index == coins.length) {
            if (count > 0) {
                // Store LCM and Sign directly into adjacent slots of the primitive array
                packedSubsets[subsetCount++] = currentLcm;
                packedSubsets[subsetCount++] = (count % 2 == 1) ? 1 : -1;
            }
            return;
        }

        // Branch 1: Skip current coin
        generateSubsets(coins, index + 1, currentLcm, count, maxLimit);

        // Branch 2: Take current coin (with inline overflow pruning)
        long g = gcd(currentLcm, coins[index]);
        if (currentLcm / g <= maxLimit / coins[index]) {
            generateSubsets(coins, index + 1, (currentLcm / g) * coins[index], count + 1, maxLimit);
        }
    }

    // High performance sequential execution using raw arrays
    private long countMultiples(long mid) {
        long count = 0;
        // Step by 2 to process both elements of the packed configuration 
        for (int i = 0; i < subsetCount; i += 2) {
            count += (mid / packedSubsets[i]) * packedSubsets[i + 1];
        }
        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
