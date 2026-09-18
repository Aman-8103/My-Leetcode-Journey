class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        Arrays.fill(left, n);

        // Record the first and last occurrence for each character
        for (int i = 0; i < n; ++i) {
            int idx = s.charAt(i) - 'a';
            left[idx] = Math.min(left[idx], i);
            right[idx] = i;
        }

        List<String> res = new ArrayList<>();
        int r = -1; // Keeps track of the end of the last added substring

        for (int i = 0; i < n; ++i) {
            int idx = s.charAt(i) - 'a';
            if (i != left[idx]) continue; // Only process at the start of a character's first occurrence

            int newR = right[idx];
            boolean valid = true;

            // Expand the right boundary if characters inside require it
            for (int j = i; j <= newR; ++j) {
                int innerIdx = s.charAt(j) - 'a';
                if (left[innerIdx] < i) { // Invalid if a character starts before our current window
                    valid = false;
                    break;
                }
                newR = Math.max(newR, right[innerIdx]);
            }

            if (valid) {
                if (i > r) {
                    res.add(s.substring(i, newR + 1));
                } else {
                    res.set(res.size() - 1, s.substring(i, newR + 1));
                }
                r = newR;
            }
        }
        return res;
    }
}
