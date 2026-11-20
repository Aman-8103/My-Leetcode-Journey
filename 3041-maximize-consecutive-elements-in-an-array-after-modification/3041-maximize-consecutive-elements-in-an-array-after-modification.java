import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        // Sort the array to process elements in increasing order
        Arrays.sort(nums);
        
        // dp maps a value `x` to the max length of a consecutive sequence ending at `x`
        Map<Integer, Integer> dp = new HashMap<>();
        int maxConsecutive = 0;

        for (int num : nums) {
            // Option 1: Increment `num` to `num + 1` (extends sequence ending at `num`)
            dp.put(num + 1, dp.getOrDefault(num, 0) + 1);
            
            // Option 2: Keep `num` as is (extends sequence ending at `num - 1`)
            dp.put(num, dp.getOrDefault(num - 1, 0) + 1);
            
            // Track the overall maximum length found so far
            maxConsecutive = Math.max(maxConsecutive, Math.max(dp.get(num), dp.get(num + 1)));
        }

        return maxConsecutive;
    }
}
