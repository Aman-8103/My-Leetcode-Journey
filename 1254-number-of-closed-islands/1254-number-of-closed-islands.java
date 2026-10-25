class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Step 1: Flood-fill all land connected to the boundary edges
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If it's a boundary cell and contains land (0)
                if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1) {
                    if (grid[i][j] == 0) {
                        dfs(grid, i, j);
                    }
                }
            }
        }
        
        // Step 2: Count the remaining isolated closed islands
        int closedIslandsCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    dfs(grid, i, j);
                    closedIslandsCount++;
                }
            }
        }
        
        return closedIslandsCount;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        // Base case: Out of bounds or already water (1)
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 1) {
            return;
        }
        
        // Mark current land cell as water to avoid redundant visits
        grid[r][c] = 1;
        
        // Recursively visit all 4 directions
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}
