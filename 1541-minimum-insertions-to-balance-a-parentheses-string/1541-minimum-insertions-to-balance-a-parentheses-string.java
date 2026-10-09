class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openCount = 0; // Tracks unmatched '('
        char[] chars = s.toCharArray();
        int n = chars.length;

        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') {
                openCount++;
            } else { // chars[i] == ')'
                // Check if the next character is also a ')'
                if (i + 1 < n && chars[i + 1] == ')') {
                    i++; // Skip the next ')' since we consume both together
                } else {
                    insertions++; // Missing one ')' to make it consecutive
                }

                // Match with an existing '('
                if (openCount > 0) {
                    openCount--;
                } else {
                    insertions++; // Missing an opening '('
                }
            }
        }

        // Each remaining unmatched '(' needs 2 insertions of ')'
        return insertions + (openCount * 2);
    }
}
