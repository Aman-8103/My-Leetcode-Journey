class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Map to store keys and their corresponding values for O(1) lookup
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // Find the closing bracket for the current key
                int j = s.indexOf(')', i);
                String key = s.substring(i + 1, j);
                // Append mapped value or '?' if the key doesn't exist
                sb.append(map.getOrDefault(key, "?"));
                i = j; // Jump index past the closing bracket
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}