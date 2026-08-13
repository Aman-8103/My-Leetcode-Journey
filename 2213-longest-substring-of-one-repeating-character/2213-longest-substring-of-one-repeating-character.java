class Solution {
    // Flattened array layout for high cache locality and performance
    private int[] lmx; // Longest prefix length
    private int[] rmx; // Longest suffix length
    private int[] mx;  // Max repeating length in this segment
    private char[] sArr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        sArr = s.toCharArray();
        
        // 4 * n is the standard upper bound size for a segment tree
        lmx = new int[4 * n];
        rmx = new int[4 * n];
        mx  = new int[4 * n];
        
        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];
        
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char newChar = queryCharacters.charAt(i);
            
            // Only update if the character actually changes
            if (sArr[idx] != newChar) {
                sArr[idx] = newChar;
                update(1, 0, n - 1, idx);
            }
            ans[i] = mx[1]; // The absolute max is always at the root node (index 1)
        }
        return ans;
    }

    private void build(int u, int l, int r) {
        if (l == r) {
            lmx[u] = rmx[u] = mx[u] = 1;
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build((u << 1) | 1, mid + 1, r);
        pushUp(u, l, r);
    }

    private void update(int u, int l, int r, int idx) {
        if (l == r) {
            return; // Substring character already altered in sArr, weights stay 1
        }
        int mid = (l + r) >> 1;
        if (idx <= mid) {
            update(u << 1, l, mid, idx);
        } else {
            update((u << 1) | 1, mid + 1, r, idx);
        }
        pushUp(u, l, r);
    }

    // Merge logic: Joins the left and right children into the parent segment
    private void pushUp(int u, int l, int r) {
        int leftChild = u << 1;
        int rightChild = (u << 1) | 1;
        int mid = (l + r) >> 1;
        
        int leftLen = mid - l + 1;
        int rightLen = r - mid;

        // 1. Inherit prefix and suffix boundaries
        lmx[u] = lmx[leftChild];
        rmx[u] = rmx[rightChild];
        mx[u] = Math.max(mx[leftChild], mx[rightChild]);

        // 2. If the adjacent boundary characters match, merge across the middle
        if (sArr[mid] == sArr[mid + 1]) {
            // Check if left child is completely uniform (all same character)
            if (lmx[leftChild] == leftLen) {
                lmx[u] = leftLen + lmx[rightChild];
            }
            // Check if right child is completely uniform
            if (rmx[rightChild] == rightLen) {
                rmx[u] = rightLen + rmx[leftChild];
            }
            // Candidate max can be the combination of left's suffix and right's prefix
            mx[u] = Math.max(mx[u], rmx[leftChild] + lmx[rightChild]);
        }
    }
}
