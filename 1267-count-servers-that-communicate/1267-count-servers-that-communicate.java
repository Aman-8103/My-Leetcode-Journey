class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Track total servers present in each row and column
        int[] rowCount = new int[m];
        int[] colCount = new int[n];
        
        // Pass 1: Count total servers in each row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }
        
        int communicatingServers = 0;
        
        // Pass 2: Check which servers can communicate
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If there's a server, and its row OR column has more than 1 server
                if (grid[i][j] == 1 && (rowCount[i] > 1 || colCount[j] > 1)) {
                    communicatingServers++;
                }
            }
        }
        
        return communicatingServers;
    }
}
