import java.util.Arrays;

public class Solution {
    public long findKthSmallest(int[] coins, int k) {
        // Sort coins to potentially exit early in our bitmask combinations
        Arrays.sort(coins);
        
        // Define binary search boundaries
        long low = 1;
        // The maximum upper bound is using the smallest coin k times
        long high = (long) coins[0] * k;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (countMultiples(coins, mid) >= k) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1; // Increase amount boundary
            }
        }
        return ans;
    }

    // Counts how many valid amounts <= maxAmount can be formed
    private long countMultiples(int[] coins, long maxAmount) {
        long count = 0;
        int n = coins.length;
        int totalSubsets = 1 << n; // 2^n total subsets

        // Bitmask from 1 to 2^n - 1 to look at every non-empty combination of coins
        for (int mask = 1; mask < totalSubsets; mask++) {
            long currentLcm = 1;
            int elementsInSubset = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    elementsInSubset++;
                    currentLcm = lcm(currentLcm, coins[i]);
                    
                    // If LCM exceeds maxAmount, its multiple count will be 0 anyway
                    if (currentLcm > maxAmount) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) continue;

            // Inclusion-Exclusion Principle logic
            if (elementsInSubset % 2 == 1) {
                count += maxAmount / currentLcm; // Odd size: add
            } else {
                count -= maxAmount / currentLcm; // Even size: subtract
            }
        }
        return count;
    }

    // Helper to calculate Greatest Common Divisor (GCD)
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Helper to calculate Least Common Multiple (LCM)
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}
