class Solution {
    public int minChanges(String s) {
        int changes = 0;
        // Step through the string two characters at a time
        for (int i = 0; i < s.length(); i += 2) {
            // If the pair doesn't match, one character needs to flip
            if (s.charAt(i) != s.charAt(i + 1)) changes++;
        }
        return changes;
    }
}
