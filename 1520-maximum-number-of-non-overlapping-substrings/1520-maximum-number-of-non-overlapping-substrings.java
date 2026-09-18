import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        Arrays.fill(left, -1);
        
        // Step 1: Find first and last occurrences
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (left[c] == -1) left[c] = i;
            right[c] = i;
        }
        
        // Step 2: Build and extend valid intervals for each present character
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (left[i] == -1) continue;
            
            int l = left[i];
            int r = right[i];
            boolean valid = true;
            
            // Expand the interval to include all required characters
            for (int j = l; j <= r; j++) {
                int c = s.charAt(j) - 'a';
                if (left[c] < l) {
                    valid = false; // Contradiction: character starts before our current root
                    break;
                }
                r = Math.max(r, right[c]);
            }
            
            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }
        
        // Step 3: Sort intervals by their end points (Greedy approach)
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));
        
        List<String> res = new ArrayList<>();
        int lastEnd = -1;
        
        // Step 4: Pick non-overlapping intervals
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            if (start > lastEnd) {
                res.add(s.substring(start, end + 1));
                lastEnd = end;
            }
        }
        
        return res;
    }
}
