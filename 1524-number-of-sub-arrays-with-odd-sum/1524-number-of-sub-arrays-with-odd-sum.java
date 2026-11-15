class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        int evenCount = 1;
        int oddCount = 0;
        int prefixSum = 0;
        int result = 0;
        for (int num : arr) {
            prefixSum += num;
            if (prefixSum % 2 == 0) {
                // Current prefix sum is even, so pair it with previous odd prefix sums
                result = (result + oddCount) % MOD;
                evenCount++;
            } else {
                // Current prefix sum is odd, so pair it with previous even prefix sums
                result = (result + evenCount) % MOD;
                oddCount++;
            }
        }
        return result;
    }
}
