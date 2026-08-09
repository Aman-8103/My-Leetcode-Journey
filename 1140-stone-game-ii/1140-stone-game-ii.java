class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // suffixSums[i] stores the total stones from pile i to the end
        int[] suffixSums = new int[n];
        suffixSums[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSums[i] = suffixSums[i + 1] + piles[i];
        }
        
        // memo[i][M] stores the max stones a player can get starting at index i with current M
        int[][] memo = new int[n][n + 1];
        
        return helper(suffixSums, 0, 1, memo);
    }
    
    private int helper(int[] suffixSums, int i, int M, int[][] memo) {
        // Base case: If the current player can take all the remaining piles
        if (i + 2 * M >= suffixSums.length) {
            return suffixSums[i];
        }
        
        // Return cached result if already calculated
        if (memo[i][M] > 0) {
            return memo[i][M];
        }
        
        int minOpponentStones = Integer.MAX_VALUE;
        
        // Try taking X piles, where 1 <= X <= 2M
        for (int X = 1; X <= 2 * M; X++) {
            // The opponent will play optimally from index i + X with new M = max(M, X)
            int opponentStones = helper(suffixSums, i + X, Math.max(M, X), memo);
            minOpponentStones = Math.min(minOpponentStones, opponentStones);
        }
        
        // Total stones available from index i minus what the opponent optimally gets
        memo[i][M] = suffixSums[i] - minOpponentStones;
        return memo[i][M];
    }
}
