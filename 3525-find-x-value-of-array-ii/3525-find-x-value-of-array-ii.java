class Solution {
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        int m = queries.length;

        // Flattened segment tree structures to completely avoid GC allocations
        int treeSize = 4 * n;
        int[] treeProd = new int[treeSize];
        int[][] treeCnt = new int[treeSize][k];

        // Build the segment tree initially
        build(nums, 0, 0, n - 1, treeProd, treeCnt, k);

        int[] ans = new int[m];
        
        // Reusable scratchpad arrays to hold query fragment merges without allocation
        int[] queryResCnt = new int[k];
        int[] tempCnt = new int[k];

        for (int i = 0; i < m; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // 1. Persistent point update
            update(0, 0, n - 1, idx, val, treeProd, treeCnt, k);

            // 2. Reset query result buffers
            for (int j = 0; j < k; j++) queryResCnt[j] = 0;
            
            // 3. Query the frequency counts from 'start' to the end of the array
            query(0, 0, n - 1, start, n - 1, treeProd, treeCnt, k, queryResCnt, tempCnt);

            ans[i] = queryResCnt[x];
        }

        return ans;
    }

    private void build(int[] nums, int node, int l, int r, int[] treeProd, int[][] treeCnt, int k) {
        if (l == r) {
            int rem = nums[l] % k;
            treeProd[node] = rem;
            treeCnt[node][rem] = 1;
            return;
        }
        int mid = (l + r) >> 1;
        int leftChild = (node << 1) + 1;
        int rightChild = (node << 1) + 2;

        build(nums, leftChild, l, mid, treeProd, treeCnt, k);
        build(nums, rightChild, mid + 1, r, treeProd, treeCnt, k);

        merge(node, leftChild, rightChild, treeProd, treeCnt, k);
    }

    private void update(int node, int l, int r, int pos, int val, int[] treeProd, int[][] treeCnt, int k) {
        if (l == r) {
            int rem = val % k;
            for (int j = 0; j < k; j++) treeCnt[node][j] = 0;
            treeProd[node] = rem;
            treeCnt[node][rem] = 1;
            return;
        }
        int mid = (l + r) >> 1;
        int leftChild = (node << 1) + 1;
        int rightChild = (node << 1) + 2;

        if (pos <= mid) {
            update(leftChild, l, mid, pos, val, treeProd, treeCnt, k);
        } else {
            update(rightChild, mid + 1, r, pos, val, treeProd, treeCnt, k);
        }

        merge(node, leftChild, rightChild, treeProd, treeCnt, k);
    }

    private int query(int node, int l, int r, int ql, int qr, int[] treeProd, int[][] treeCnt, int k, int[] resCnt, int[] tempCnt) {
        if (ql <= l && r <= qr) {
            boolean isEmpty = true;
            for (int j = 0; j < k; j++) {
                if (resCnt[j] != 0) {
                    isEmpty = false;
                    break;
                }
            }
            if (isEmpty) {
                System.arraycopy(treeCnt[node], 0, resCnt, 0, k);
                return treeProd[node];
            }

            System.arraycopy(resCnt, 0, tempCnt, 0, k);
            for (int j = 0; j < k; j++) resCnt[j] = tempCnt[j];

            return treeProd[node];
        }

        int mid = (l + r) >> 1;
        int leftChild = (node << 1) + 1;
        int rightChild = (node << 1) + 2;

        if (qr <= mid) {
            return query(leftChild, l, mid, ql, qr, treeProd, treeCnt, k, resCnt, tempCnt);
        } else if (ql > mid) {
            return query(rightChild, mid + 1, r, ql, qr, treeProd, treeCnt, k, resCnt, tempCnt);
        } else {
            int leftProd = query(leftChild, l, mid, ql, mid, treeProd, treeCnt, k, resCnt, tempCnt);
            System.arraycopy(resCnt, 0, tempCnt, 0, k);
            
            int rightChildProd = treeProd[rightChild]; 
            if (mid + 1 >= ql && r <= qr) {
                for (int i = 0; i < k; i++) {
                    int nextRem = (leftProd * i) % k;
                    resCnt[nextRem] += treeCnt[rightChild][i];
                }
                return (leftProd * rightChildProd) % k;
            } else {
                int[] isolatedRightCnt = new int[k];
                int actualRightProd = query(rightChild, mid + 1, r, mid + 1, qr, treeProd, treeCnt, k, isolatedRightCnt, new int[k]);
                
                for (int i = 0; i < k; i++) {
                    int nextRem = (leftProd * i) % k;
                    tempCnt[nextRem] += isolatedRightCnt[i];
                }
                System.arraycopy(tempCnt, 0, resCnt, 0, k);
                return (leftProd * actualRightProd) % k;
            }
        }
    }

    private void merge(int parent, int left, int right, int[] treeProd, int[][] treeCnt, int k) {
        int leftProd = treeProd[left];
        treeProd[parent] = (leftProd * treeProd[right]) % k;

        System.arraycopy(treeCnt[left], 0, treeCnt[parent], 0, k);

        for (int i = 0; i < k; i++) {
            int nextRem = (leftProd * i) % k;
            treeCnt[parent][nextRem] += treeCnt[right][i];
        }
    }
}
