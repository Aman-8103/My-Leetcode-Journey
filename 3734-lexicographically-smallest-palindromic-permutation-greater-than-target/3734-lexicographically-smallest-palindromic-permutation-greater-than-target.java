class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;
        int[] totalFreq = new int[26];
        
        for (int i = 0; i < n; i++) {
            totalFreq[s.charAt(i) - 'a']++;
        }
        
        // Validate if a palindrome can be formed (at most one odd frequency)
        int oddCount = 0;
        int oddIdx = -1;
        for (int i = 0; i < 26; i++) {
            if (totalFreq[i] % 2 != 0) {
                oddCount++;
                oddIdx = i;
            }
        }
        if (oddCount > 1) {
            return "";
        }
        
        // Character pool allocated to the left half
        int[] halfFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            halfFreq[i] = totalFreq[i] / 2;
        }
        
        char midChar = (oddIdx != -1) ? (char) ('a' + oddIdx) : '#';
        
        // --- Case 1: Try to match target's left half exactly ---
        char[] leftHalf = new char[half];
        boolean canMatchPerfectly = true;
        int[] tempFreq = halfFreq.clone();
        
        for (int i = 0; i < half; i++) {
            int targetCharIdx = target.charAt(i) - 'a';
            if (tempFreq[targetCharIdx] > 0) {
                leftHalf[i] = target.charAt(i);
                tempFreq[targetCharIdx]--;
            } else {
                canMatchPerfectly = false;
                break;
            }
        }
        
        if (canMatchPerfectly) {
            String candidate = buildPalindrome(leftHalf, midChar);
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }
        
        // --- Case 2: Scan from right to left to find the best pivot to increment ---
        for (int i = half - 1; i >= 0; i--) {
            // Re-verify if the prefix up to i-1 can match target
            int[] currentPool = halfFreq.clone();
            boolean prefixValid = true;
            for (int j = 0; j < i; j++) {
                int chIdx = target.charAt(j) - 'a';
                if (currentPool[chIdx] > 0) {
                    leftHalf[j] = target.charAt(j);
                    currentPool[chIdx]--;
                } else {
                    prefixValid = false;
                    break;
                }
            }
            
            if (!prefixValid) {
                continue; // Cannot even form the prefix up to this point
            }
            
            // Try to find the smallest character strictly greater than target.charAt(i)
            int targetCharIdx = target.charAt(i) - 'a';
            int replacementIdx = -1;
            for (int c = targetCharIdx + 1; c < 26; c++) {
                if (currentPool[c] > 0) {
                    replacementIdx = c;
                    break;
                }
            }
            
            // If found, place it, and fill the remaining spots greedily with the smallest values
            if (replacementIdx != -1) {
                leftHalf[i] = (char) ('a' + replacementIdx);
                currentPool[replacementIdx]--;
                
                int ptr = i + 1;
                for (int c = 0; c < 26; c++) {
                    while (currentPool[c] > 0) {
                        leftHalf[ptr++] = (char) ('a' + c);
                        currentPool[c]--;
                    }
                }
                return buildPalindrome(leftHalf, midChar);
            }
        }
        
        return "";
    }
    
    // Helper to mirror the left half and assemble the full palindrome
    private String buildPalindrome(char[] leftHalf, char midChar) {
        StringBuilder sb = new StringBuilder();
        sb.append(leftHalf);
        if (midChar != '#') {
            sb.append(midChar);
        }
        for (int i = leftHalf.length - 1; i >= 0; i--) {
            sb.append(leftHalf[i]);
        }
        return sb.toString();
    }
}
