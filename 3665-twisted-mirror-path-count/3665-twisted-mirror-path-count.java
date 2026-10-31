
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // dp[i][j][0] = ways arriving from left
        // dp[i][j][1] = ways arriving from above
        int[][][] dp = new int[m][n][2];

        // First move from starting cell
        if (n > 1) dp[0][1][0] = 1;
        if (m > 1) dp[1][0][1] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) continue;
                if (i == m - 1 && j == n - 1) continue;

                int right = dp[i][j][0];
                int down = dp[i][j][1];

                if (grid[i][j] == 0) {
                    // Empty cell: move right or down
                    if (j + 1 < n) {
                        dp[i][j + 1][0] =
                            (int)((dp[i][j + 1][0] + (long)right + down) % MOD);
                    }

                    if (i + 1 < m) {
                        dp[i + 1][j][1] =
                            (int)((dp[i + 1][j][1] + (long)right + down) % MOD);
                    }
                } else {
                    // Mirror: right -> down
                    if (i + 1 < m) {
                        dp[i + 1][j][1] =
                            (int)((dp[i + 1][j][1] + (long)right) % MOD);
                    }

                    // Mirror: down -> right
                    if (j + 1 < n) {
                        dp[i][j + 1][0] =
                            (int)((dp[i][j + 1][0] + (long)down) % MOD);
                    }
                }
            }
        }

        return (int)(((long)dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD);
    }
}
