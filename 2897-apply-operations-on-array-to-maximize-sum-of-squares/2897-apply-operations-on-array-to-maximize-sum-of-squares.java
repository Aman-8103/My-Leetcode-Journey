import java.util.List;

public class Solution {
    public int maxSum(List<Integer> nums, int k) {
        // Step 1: Count occurrences of set bits at each position (0 to 30)
        int[] bitCounts = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                bitCounts[i] += (num >> i) & 1;
            }
        }

        long totalSumOfSquares = 0;
        int MOD = 1_000_000_007;

        // Step 2: Greedily construct k largest numbers
        for (int i = 0; i < k; i++) {
            long currentNumber = 0;
            for (int j = 0; j < 32; j++) {
                // If there are still bits remaining for position j, use it
                if (bitCounts[j] > 0) {
                    currentNumber |= (1L << j);
                    bitCounts[j]--; // Consume the bit
                }
            }
            // Step 3: Add the square of the constructed number to total sum
            long square = (currentNumber * currentNumber) % MOD;
            totalSumOfSquares = (totalSumOfSquares + square) % MOD;
        }

        return (int) totalSumOfSquares;
    }
}
