class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) return "";
        char[] ans = new char[num];

        for (int i = 0; i < num; i++) {
            int digit = Math.min(9, sum);
            ans[i] = (char) ('0' + digit);
            sum -= digit;
        }
        return new String(ans);
    }
}