public class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        char[] chars = s.toCharArray();
        int n = chars.length;
        int left = 0, right = 0, maxLength = 0;

        // Pass 1: Forward Scan
        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }

        left = right = 0;
        // Pass 2: Backward Scan (catches cases like "(()")
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }

        return maxLength;
    }
}
