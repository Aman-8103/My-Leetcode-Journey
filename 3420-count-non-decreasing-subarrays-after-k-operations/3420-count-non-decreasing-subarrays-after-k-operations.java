class Solution {
    public long countNonDecreasingSubarrays(int[] nums, long k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        
        // Primitive array acting as a monotonic deque (avoids Integer autoboxing)
        int[] dq = new int[n];
        int head = 0; // Points to the front (largest element's index)
        int tail = 0; // Points to the next insertion slot at the back
        
        int j = n - 1;
        
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            
            // Pop smaller elements from the back and accumulate cost
            while (head < tail && nums[dq[tail - 1]] < num) {
                int l = dq[--tail];
                int r = (head == tail) ? j + 1 : dq[tail - 1];
                cost += (long) (r - l) * (num - nums[l]);
            }
            dq[tail++] = i;
            
            // Shrink window from the right if total cost exceeds k
            while (cost > k) {
                int frontIdx = dq[head];
                cost -= nums[frontIdx] - nums[j];
                if (frontIdx == j) {
                    head++;
                }
                j--;
            }
            
            ans += (j - i + 1);
        }
        
        return ans;
    }
}
