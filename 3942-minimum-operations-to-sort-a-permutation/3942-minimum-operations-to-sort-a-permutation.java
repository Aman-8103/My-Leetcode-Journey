class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length, zero = 0, f = 1, b = 1;
        for (int i = 0; i < n; i++) if (nums[i] == 0) zero = i;
        
        for (int i = 0; i < n; i++) {
            if (nums[(zero + i) % n] != i) f = 0;
            if (nums[(zero - i + n) % n] != i) b = 0;
        }
        
        if (f == 0 && b == 0) return -1;
        
        int ans = Integer.MAX_VALUE;
        if (f == 1) ans = Math.min(zero, n - zero + 2);
        if (b == 1) ans = Math.min(ans, Math.min(zero + 2, n - zero));
        return ans;
    }
}
