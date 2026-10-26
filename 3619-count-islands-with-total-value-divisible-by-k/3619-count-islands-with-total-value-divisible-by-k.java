class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    // Accumulate sum and destroy island values in-place
                    long totalSum = dfs(grid, i, j, m, n);
                    if (totalSum % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private long dfs(int[][] grid, int r, int c, int m, int n) {
        long sum = grid[r][c];
        grid[r][c] = 0; // Mark as visited in-place

        // Check bounds before diving deeper to minimize method invocation overhead
        if (r + 1 < m && grid[r + 1][c] > 0) sum += dfs(grid, r + 1, c, m, n);
        if (r - 1 >= 0 && grid[r - 1][c] > 0) sum += dfs(grid, r - 1, c, m, n);
        if (c + 1 < n && grid[r][c + 1] > 0) sum += dfs(grid, r, c + 1, m, n);
        if (c - 1 >= 0 && grid[r][c - 1] > 0) sum += dfs(grid, r, c - 1, m, n);

        return sum;
    }
}
