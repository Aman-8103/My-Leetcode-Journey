import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, "", 0, 0, n);
        return result;
    }

    private void generate(List<String> result, String current, int open, int close, int max) {
        // Base case: string is complete
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        // Add open parenthesis if we have remaining slots
        if (open < max) {
            generate(result, current + "(", open + 1, close, max);
        }

        // Add close parenthesis if it can pair with an unmatched open one
        if (close < open) {
            generate(result, current + ")", open, close + 1, max);
        }
    }
}
