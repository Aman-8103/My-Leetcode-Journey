class Solution {
    private int[] prefix;
    private int[][] memo;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }
        
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(memo[i], -1);
        }
        
        return solve(0, n - 1);
    }
    
    private int getSum(int i, int j) {
        return prefix[j + 1] - prefix[i];
    }
    
    private int solve(int i, int j) {
        if (i == j) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        
        int maxScore = 0;
        for (int p = i; p < j; p++) {
            int leftSum = getSum(i, p);
            int rightSum = getSum(p + 1, j);
            
            if (leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + solve(i, p));
            } else if (leftSum > rightSum) {
                maxScore = Math.max(maxScore, rightSum + solve(p + 1, j));
            } else {
                maxScore = Math.max(maxScore, leftSum + Math.max(solve(i, p), solve(p + 1, j)));
            }
        }
        
        return memo[i][j] = maxScore;
    }
}
