class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Maximum possible XOR value since grid[i][j] <= 1023
        final int MAX_XOR = 1024; 
        
        // Rolling array: Only keep the results of the current row and the previous row
        boolean[][] dp = new boolean[n][MAX_XOR];
        
        // Initialize the starting position (0, 0)
        dp[0][grid[0][0]] = true;
        
        // Initialize the first row (can only move right)
        for (int j = 1; j < n; j++) {
            int val = grid[0][j];
            for (int k = 0; k < MAX_XOR; k++) {
                if (dp[j - 1][k]) {
                    dp[j][k ^ val] = true;
                }
            }
        }
        
        // Fill out the remaining rows
        for (int i = 1; i < m; i++) {
            boolean[][] nextDp = new boolean[n][MAX_XOR];
            
            // Handle the first column of the row (can only move down)
            int firstColVal = grid[i][0];
            for (int k = 0; k < MAX_XOR; k++) {
                if (dp[0][k]) {
                    nextDp[0][k ^ firstColVal] = true;
                }
            }
            
            // Handle the remaining columns (can move down or right)
            for (int j = 1; j < n; j++) {
                int val = grid[i][j];
                for (int k = 0; k < MAX_XOR; k++) {
                    // Option 1: Came from above (dp[j][k])
                    // Option 2: Came from the left (nextDp[j - 1][k])
                    if (dp[j][k] || nextDp[j - 1][k]) {
                        nextDp[j][k ^ val] = true;
                    }
                }
            }
            
            // Move the current row state to the previous row tracker
            dp = nextDp;
        }
        
        // Scan for the minimum valid XOR sum at the bottom-right cell
        for (int k = 0; k < MAX_XOR; k++) {
            if (dp[n - 1][k]) {
                return k;
            }
        }
        
        return 0;
    }
}
