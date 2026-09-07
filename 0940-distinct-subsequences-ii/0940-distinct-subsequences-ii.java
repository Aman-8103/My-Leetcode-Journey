class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        
        // dp[i] stores the number of distinct subsequences ending with character ('a' + i)
        int[] dp = new int[26];
        
        // Tracks the total number of distinct non-empty subsequences found so far
        int totalSubsequences = 0;
        
        for (int i = 0; i < s.length(); ++i) {
            int charIndex = s.charAt(i) - 'a';
            
            // 1. Calculate how many new subsequences we can create by adding this character
            // We can append s.charAt(i) to all currently existing subsequences (totalSubsequences) 
            // Plus, this single character can form a brand new standalone subsequence (+ 1)
            int currentNewCount = (totalSubsequences + 1) % MOD;
            
            // 2. To get the net addition, subtract what this character previously contributed
            // This prevents duplicate subsequences from being counted twice
            int netAdded = (currentNewCount - dp[charIndex] + MOD) % MOD;
            
            // 3. Update our global running total
            totalSubsequences = (totalSubsequences + netAdded) % MOD;
            
            // 4. Record the new count of subsequences ending with this character
            dp[charIndex] = currentNewCount;
        }
        
        return totalSubsequences;
    }
}
