import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        
        // Size the DP array based on the largest element (nums[n - 1] + 2)
        int maxVal = nums[nums.length - 1];
        int[] dp = new int[maxVal + 2];
        
        int maxConsecutive = 0;

        for (int num : nums) {
            // Option 1: Increment num -> num + 1
            int incLen = dp[num] + 1;
            dp[num + 1] = incLen;
            
            // Option 2: Keep num as is
            int keepLen = dp[num - 1] + 1;
            dp[num] = keepLen;
            
            // Track maximum inline without extra array lookups
            if (incLen > maxConsecutive) maxConsecutive = incLen;
            if (keepLen > maxConsecutive) maxConsecutive = keepLen;
        }

        return maxConsecutive;
    }
}
