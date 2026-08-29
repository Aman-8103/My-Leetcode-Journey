class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        // Sort indices based on the values in nums
        Arrays.sort(idx, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] ans = new int[n];
        for (int i = 0; i < n;) {
            int j = i + 1;
            // Find all indices that belong to the same group
            while (j < n && nums[idx[j]] - nums[idx[j - 1]] <= limit) {
                j++;
            }

            // Extract the sub-array of indices for this group and sort them
            Integer[] groupIndices = Arrays.copyOfRange(idx, i, j);
            Integer[] sortedIndices = groupIndices.clone();
            Arrays.sort(sortedIndices);

            // Assign the smallest values to the original indices in ascending order of index
            for (int k = 0; k < sortedIndices.length; k++) {
                ans[sortedIndices[k]] = nums[groupIndices[k]];
            }
            i = j;
        }
        return ans;
    }
}
