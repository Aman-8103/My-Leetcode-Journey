class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int maxCells = m * n;
        
        // Reusable stack array to completely eliminate object allocations
        // Packs a 2D coordinate into a single integer: (row * n + col)
        int[] stack = new int[maxCells];
        int islandCount = 0;

        for (int i = 0; i < m; i++) {
            int[] row = grid[i]; // Local variable reference to minimize row lookup overhead
            for (int j = 0; j < n; j++) {
                if (row[j] > 0) {
                    int top = 0;
                    stack[top] = i * n + j;
                    int islandSumModK = 0;

                    while (top >= 0) {
                        int curr = stack[top--];
                        int r = curr / n;
                        int c = curr % n;

                        if (grid[r][c] > 0) {
                            // Accumulate directly using modulo math to keep operations strictly inside integer primitives
                            islandSumModK = (islandSumModK + grid[r][c]) % k;
                            grid[r][c] = 0; // Destroy cell in-place

                            // Check all 4 directions and push valid land to the stack immediately
                            if (r + 1 < m && grid[r + 1][c] > 0) stack[++top] = (r + 1) * n + c;
                            if (r - 1 >= 0 && grid[r - 1][c] > 0) stack[++top] = (r - 1) * n + c;
                            if (c + 1 < n && grid[r][c + 1] > 0) stack[++top] = r * n + (c + 1);
                            if (c - 1 >= 0 && grid[r][c - 1] > 0) stack[++top] = r * n + (c - 1);
                        }
                    }

                    if (islandSumModK == 0) {
                        islandCount++;
                    }
                }
            }
        }
        return islandCount;
    }
}
