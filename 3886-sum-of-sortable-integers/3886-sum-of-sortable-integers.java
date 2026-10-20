class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int totalSum = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;
            
            if (isSortable(nums, n, k)) {
                totalSum += k;
            }
        }
        
        return totalSum;
    }

    private boolean isSortable(int[] nums, int n, int k) {
        int prevMax = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i += k) {
            // Find breaks in monotonicity inside the block of size k
            int drops = 0;
            int minVal = Integer.MAX_VALUE;
            int maxVal = Integer.MIN_VALUE;
            
            for (int j = 0; j < k; j++) {
                int curr = nums[i + j];
                minVal = Math.min(minVal, curr);
                maxVal = Math.max(maxVal, curr);
                
                int next = nums[i + (j + 1) % k];
                if (curr > next) {
                    drops++;
                }
            }
            
            // A cyclically rotatable block can have at most 1 drop
            if (drops > 1) {
                return false;
            }
            
            // Current block's minimum must be >= previous block's maximum
            if (minVal < prevMax) {
                return false;
            }
            
            prevMax = maxVal;
        }
        
        return true;
    }
}
