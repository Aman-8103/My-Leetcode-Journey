import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Object> stack = new Stack<>();
        int i = 0;
        int n = expression.length();
        
        while (i < n) {
            char c = expression.charAt(i);
            
            if (c == '{' || c == ',') {
                stack.push(c);
                i++;
            } else if (c == '}') {
                // 1. Process all elements up to the matching '{'
                List<TreeSet<String>> list = new ArrayList<>();
                while (!stack.isEmpty() && !stack.peek().equals('{')) {
                    Object val = stack.pop();
                    if (val instanceof TreeSet) {
                        list.add((TreeSet<String>) val);
                    }
                    // Skip commas, they are handled by grouping the list
                }
                if (!stack.isEmpty()) stack.pop(); // Remove '{'
                
                // 2. Merge elements separated by commas (Union operation)
                TreeSet<String> merged = new TreeSet<>();
                for (TreeSet<String> set : list) {
                    merged.addAll(set);
                }
                
                // 3. Concatenate with the preceding set if it's a word/set (Cartesian Product)
                autoCombine(stack, merged);
                i++;
            } else {
                // Parse consecutive alphabetic characters as a single word
                StringBuilder sb = new StringBuilder();
                while (i < n && Character.isLetter(expression.charAt(i))) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                TreeSet<String> current = new TreeSet<>();
                current.add(sb.toString());
                
                // Concatenate with preceding set if applicable
                autoCombine(stack, current);
            }
        }
        
        // The remaining element on the stack is our final combined, sorted set
        return new ArrayList<>((TreeSet<String>) stack.pop());
    }
    
    private void autoCombine(Stack<Object> stack, TreeSet<String> current) {
        if (!stack.isEmpty() && stack.peek() instanceof TreeSet) {
            TreeSet<String> prev = (TreeSet<String>) stack.pop();
            TreeSet<String> combined = new TreeSet<>();
            for (String p : prev) {
                for (String c : current) {
                    combined.add(p + c);
                }
            }
            stack.push(combined);
        } else {
            stack.push(current);
        }
    }
}
