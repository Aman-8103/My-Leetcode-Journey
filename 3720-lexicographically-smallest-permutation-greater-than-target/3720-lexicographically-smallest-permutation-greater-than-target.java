class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        StringBuilder ans = new StringBuilder();
        if (backtrack(freq, target, 0, ans)) {
            return ans.toString();
        }
        return "";
    }
    
    private boolean backtrack(int[] freq, String target, int idx, StringBuilder ans) {
        if (idx == target.length()) {
            return false; 
        }
        
        int tChar = target.charAt(idx) - 'a';
        
        // Try exact match first if available
        if (freq[tChar] > 0) {
            freq[tChar]--;
            ans.append((char) ('a' + tChar));
            if (backtrack(freq, target, idx + 1, ans)) {
                return true;
            }
            // Backtrack
            ans.deleteCharAt(ans.length() - 1);
            freq[tChar]++;
        }
        
        // Try a strictly larger character at this position
        for (int i = tChar + 1; i < 26; i++) {
            if (freq[i] > 0) {
                freq[i]--;
                ans.append((char) ('a' + i));
                // Append the remaining characters in sorted (lexicographically smallest) order
                for (int j = 0; j < 26; j++) {
                    while (freq[j] > 0) {
                        freq[j]--;
                        ans.append((char) ('a' + j));
                    }
                }
                return true;
            }
        }
        
        return false;
    }
}
