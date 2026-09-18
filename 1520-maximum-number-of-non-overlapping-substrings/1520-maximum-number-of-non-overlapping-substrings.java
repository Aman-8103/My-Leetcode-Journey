import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        Arrays.fill(left, -1);
        
        // Step 1: Find first and last occurrences of each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (left[c] == -1) left[c] = i;
            right[c] = i;
        }
        
        // Step 2: Expand boundaries to find valid closure for all 26 characters
        int[] validRight = new int[26];
        Arrays.fill(validRight, -1);
        
        for (int i = 0; i < 26; i++) {
            if (left[i] == -1) continue;
            
            int l = left[i];
            int r = right[i];
            boolean isValid = true;
            
            for (int j = l; j <= r; j++) {
                int c = s.charAt(j) - 'a';
                if (left[c] < l) { 
                    isValid = false; // Expands leftwards out of bounds
                    break;
                }
                r = Math.max(r, right[c]);
            }
            if (isValid) {
                validRight[i] = r;
            }
        }
        
        // Step 3: Collect result greedily from right to left to avoid sorting
        List<String> res = new ArrayList<>();
        int lastStart = n;
        
        // Iterate backwards through the string
        for (int i = n - 1; i >= 0; i--) {
            int c = s.charAt(i) - 'a';
            // If this is the starting position of character 'c' and it yields a valid interval
            if (i == left[c] && validRight[c] != -1) {
                int r = validRight[c];
                // Check if this interval fits without overlapping previously picked segments
                if (r < lastStart) {
                    res.add(s.substring(i, r + 1));
                    lastStart = i;
                }
            }
        }
        
        // Reverse the list since we collected it backwards
        Collections.reverse(res);
        return res;
    }
}
