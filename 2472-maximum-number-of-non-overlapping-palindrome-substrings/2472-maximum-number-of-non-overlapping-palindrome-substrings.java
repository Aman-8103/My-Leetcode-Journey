class Solution {

    public int maxPalindromes(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int ans = 0;
        int start = 0;

        for (int i = k - 1; i < n; ++i) {
            int l = i - k + 1;
            if (l >= start && check(chars, l, i)) {
                ++ans;
                start = i + 1;
                continue;
            }

            l = i - k;
            if (l >= start && check(chars, l, i)) {
                ++ans;
                start = i + 1;
            }
        }

        return ans;
    }

    private boolean check(char[] chars, int l, int i) {
        while (l < i) {
            if (chars[l++] != chars[i--]) {
                return false;
            }
        }
        return true;
    }
}
