import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        // Start scanning with standard order matching '(' and ')'
        dfs(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    private void dfs(String s, List<String> ans, int lastI, int lastJ, char[] par) {
        int balance = 0;
        for (int i = lastI; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) balance++;
            if (s.charAt(i) == par[1]) balance--;
            if (balance >= 0) continue; // String is still valid so far

            // Prefix is invalid: we have more close brackets than open brackets!
            for (int j = lastJ; j <= i; j++) {
                // Find a close bracket to remove. 
                // Skip duplicates: only remove the first one in a consecutive streak.
                if (s.charAt(j) == par[1] && (j == lastJ || s.charAt(j - 1) != par[1])) {
                    // Recurse with the modified string, passing current i and j to avoid re-scanning
                    dfs(s.substring(0, j) + s.substring(j + 1), ans, i, j, par);
                }
            }
            return; // Stop processing this branch since we branched out variations
        }

        // If the forward check passes, reverse the string to check the other direction
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            // First time finishing: flip characters to clear out extra open brackets '('
            dfs(reversed, ans, 0, 0, new char[]{')', '('});
        } else {
            // Second time finishing: string is completely valid. Add it to the answer.
            ans.add(reversed);
        }
    }
}
