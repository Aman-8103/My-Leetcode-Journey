import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, long k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        
        // Store indices of nums in a monotonic stack/deque
        Deque<Integer> dq = new ArrayDeque<>();
        int j = n - 1;
        
        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            while (!dq.isEmpty() && nums[dq.peekLast()] < num) {
                int l = dq.pollLast();
                int r = dq.isEmpty() ? j + 1 : dq.peekLast();
                cost += (long) (r - l) * (num - nums[l]);
            }
            dq.addLast(i);
            
            // Shrink the window from the right if cost exceeds k
            while (cost > k) {
                cost -= nums[dq.peekFirst()] - nums[j];
                if (dq.peekFirst() == j) {
                    dq.pollFirst();
                }
                j--;
            }
            ans += (j - i + 1);
        }
        return ans;
    }
}
