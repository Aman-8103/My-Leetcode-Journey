class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen = Math.max(0, minOpen - 1);
                maxOpen--;
            } else { // c == '*'
                minOpen = Math.max(0, minOpen - 1); // Treat '*' as ')' or empty
                maxOpen++;                          // Treat '*' as '('
            }
            if (maxOpen < 0) {
                return false; // Too many closing parentheses
            }
        }
        return minOpen == 0; // All open parentheses are balanced
    }
}