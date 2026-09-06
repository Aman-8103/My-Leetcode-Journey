class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // If s is shorter than t, it's impossible to form t from s
        if (m < n) {
            return 0;
        }
        
        // dp[j] stores the number of distinct subsequences matching t[0...j-1]
        int[] dp = new int[n + 1];
        
        // Base case: An empty string t is a subsequence of any prefix of s
        dp[0] = 1; 
        
        // Process each character of string s
        for (int i = 0; i < m; i++) {
            char charS = s.charAt(i);
            
            // Traverse t backwards to avoid using the same character multiple times
            for (int j = n; j > 0; j--) {
                char charT = t.charAt(j - 1);
                
                // If characters match, we add the possibilities from the previous match
                if (charS == charT) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return dp[n];
    }
}
