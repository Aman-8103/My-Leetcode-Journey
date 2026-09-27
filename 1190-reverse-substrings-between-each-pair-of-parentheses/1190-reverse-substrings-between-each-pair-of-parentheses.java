import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(sb);
                sb = new StringBuilder();
            } else if (c == ')') {
                sb.reverse();
                if (!stack.isEmpty()) {
                    StringBuilder temp = stack.pop();
                    temp.append(sb);
                    sb = temp;
                }
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
