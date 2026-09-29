class Solution {
    private int m, n;
    private char[][] grid;
    private boolean[][][] visited;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        
        // Fast Pruning: 
        // 1. Total steps (m + n - 1) must be even to form valid balanced parentheses.
        // 2. The start cell cannot be ')' and the target cell cannot be '('.
        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        // Max possible count of open brackets needed to track is at most half the path
        int maxOpen = (m + n) / 2;
        this.visited = new boolean[m][n][maxOpen + 1];
        
        return dfs(0, 0, 0);
    }

    private boolean dfs(int r, int c, int count) {
        // Track the current balance of open '(' parentheses
        count += (grid[r][c] == '(') ? 1 : -1;
        
        // Balance cannot drop below zero
        if (count < 0) {
            return false;
        }
        
        // Target cell condition
        if (r == m - 1 && c == n - 1) {
            return count == 0;
        }
        
        // Fast Pruning: Remaining steps must be able to exactly balance out the count
        int remainingSteps = (m - 1 - r) + (n - 1 - c);
        if (count > remainingSteps || count > (m + n) / 2) {
            return false;
        }
        
        // If we have already explored this (row, col, count) state, terminate early
        if (visited[r][c][count]) {
            return false;
        }
        visited[r][c][count] = true;
        
        // Exploit short-circuiting: try moving down first, then right
        if (r + 1 < m && dfs(r + 1, c, count)) return true;
        if (c + 1 < n && dfs(r, c + 1, count)) return true;
        
        return false;
    }
}
