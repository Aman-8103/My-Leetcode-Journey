class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        
        // Find the maximum difference dynamically to constrain the bucket size
        int maxDiff = 0;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            if (diff > maxDiff) {
                maxDiff = diff;
            }
        }
        
        // Count frequencies of each difference
        int[] counts = new int[maxDiff + 1];
        long totalDiffSum = 0;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            counts[diff]++;
            totalDiffSum += diff;
        }
        
        // If total operations can reduce all differences to 0
        if (totalDiffSum <= k) {
            return 0;
        }
        
        // Level elements arithmetically from the top down
        int currentCount = 0;
        for (int i = maxDiff; i > 0; i--) {
            currentCount += counts[i];
            
            // Calculate how many total operations are needed to drop all 
            // accumulated elements from the current level 'i' to level 'i - 1'
            long totalOpsNeeded = (long) currentCount;
            
            if (k >= totalOpsNeeded) {
                // If we have enough k to level the entire row down, do it instantly
                k -= totalOpsNeeded;
            } else {
                // If k runs out mid-level, distribute the remaining operations
                long targetLevelReducedCount = k / currentCount;
                long remainder = k % currentCount;
                
                // Calculate final counts for the split levels
                long finalHighVal = i - targetLevelReducedCount;
                long finalLowVal = finalHighVal - 1;
                
                long highCount = currentCount - remainder;
                long lowCount = remainder;
                
                // Accumulate the final answer for the remaining levels below 'i'
                long result = (highCount * finalHighVal * finalHighVal) + (lowCount * finalLowVal * finalLowVal);
                for (int j = i - 1; j > 0; j--) {
                    if (counts[j] > 0) {
                        result += (long) counts[j] * j * j;
                    }
                }
                return result;
            }
        }
        
        return 0;
    }
}
