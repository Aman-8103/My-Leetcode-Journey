import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        
        // Quick O(N) check for answer == 1 using a primitive frequency array
        // Constraints: 1 <= nums[i] <= 10^5
        int maxVal = 0;
        for (int x : nums) {
            if (x > maxVal) maxVal = x;
        }
        int[] freq = new int[maxVal + 1];
        for (int x : nums) freq[x]++;
        for (int x : nums) {
            if (freq[x] == 1) return 1;
        }

        // Suffix Automaton arrays (at most 2*N states)
        int maxStates = 2 * n + 1;
        int[] len = new int[maxStates];
        int[] link = new int[maxStates];
        int[] cnt = new int[maxStates];
        
        // Using flat arrays / open-addressed hash table or Int2Int map for transitions
        // Since alphabet size <= 10^5, an array of HashMaps or custom primitive map is fast
        @SuppressWarnings("unchecked")
        HashMap<Integer, Integer>[] next = new HashMap[maxStates];
        for (int i = 0; i < maxStates; i++) {
            next[i] = new HashMap<>(4);
        }

        int size = 1;
        int last = 0;
        link[0] = -1;

        // Build Suffix Automaton in O(N)
        for (int c : nums) {
            int cur = size++;
            len[cur] = len[last] + 1;
            cnt[cur] = 1;
            int p = last;

            while (p != -1 && !next[p].containsKey(c)) {
                next[p].put(c, cur);
                p = link[p];
            }

            if (p == -1) {
                link[cur] = 0;
            } else {
                int q = next[p].get(c);
                if (len[p] + 1 == len[q]) {
                    link[cur] = q;
                } else {
                    int clone = size++;
                    len[clone] = len[p] + 1;
                    next[clone].putAll(next[q]);
                    link[clone] = link[q];
                    while (p != -1 && next[p].get(c) == q) {
                        next[p].put(c, clone);
                        p = link[p];
                    }
                    link[q] = clone;
                    link[cur] = clone;
                }
            }
            last = cur;
        }

        // Counting sort states by length for topological order in O(N)
        int[] bucket = new int[n + 1];
        for (int i = 0; i < size; i++) bucket[len[i]]++;
        for (int i = 1; i <= n; i++) bucket[i] += bucket[i - 1];
        
        int[] order = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            order[--bucket[len[i]]] = i;
        }

        // Propagate occurrence counts bottom-up and find minimum unique length
        int ans = n;
        for (int i = size - 1; i > 0; i--) {
            int v = order[i];
            int p = link[v];
            cnt[p] += cnt[v];
            if (cnt[v] == 1) {
                int minSubarrayLen = len[p] + 1;
                if (minSubarrayLen < ans) {
                    ans = minSubarrayLen;
                }
            }
        }

        return ans;
    }
}
