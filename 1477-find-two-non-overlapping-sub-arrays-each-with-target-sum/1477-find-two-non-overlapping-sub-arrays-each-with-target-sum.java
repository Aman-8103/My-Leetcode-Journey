import java.util.Arrays;

public class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        int minTotalLength = Integer.MAX_VALUE;
        int currentSum = 0;
        int left = 0;
        int bestLeftLength = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            // Fast shrink loop
            while (currentSum > target) {
                currentSum -= arr[left++];
            }
            
            if (currentSum == target) {
                int currentLength = right - left + 1;
                
                // Check if a valid disjoint subarray exists to the left
                if (left > 0 && dp[left - 1] != Integer.MAX_VALUE) {
                    int combinedLength = currentLength + dp[left - 1];
                    if (combinedLength < minTotalLength) {
                        minTotalLength = combinedLength;
                    }
                }
                
                // Update the running best length found so far
                if (currentLength < bestLeftLength) {
                    bestLeftLength = currentLength;
                }
            }
            
            // Record the absolute minimum length seen up to the current index
            dp[right] = bestLeftLength;
        }
        
        return minTotalLength == Integer.MAX_VALUE ? -1 : minTotalLength;
    }
}
