import java.util.Arrays;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        
        int[] ans = new int[n2];
        // last[j] stores the maximum index i in word1 that matches word2[j]
        // while safely matching the suffix word2[j...] downstream.
        int[] last = new int[n2];
        Arrays.fill(last, -1);
        
        // Pass 1: Backward pass to precompute maximum greedy suffix indices
        int i = n1 - 1;
        int j = n2 - 1;
        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
            i--;
        }
        
        // Pass 2: Forward pass to build the lexicographically smallest sequence
        boolean canSkip = true;
        j = 0;
        
        for (i = 0; i < n1; i++) {
            if (j == n2) {
                break;
            }
            
            // Case 1: Elements match naturally
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            } 
            // Case 2: Mismatch, try using our single modification
            else if (canSkip && (j == n2 - 1 || i < last[j + 1])) {
                canSkip = false; // Consume our one-time wild-card substitution
                ans[j] = i;
                j++;
            }
        }
        
        // If we successfully matched all characters of word2, return the sequence
        return j == n2 ? ans : new int[0];
    }
}
