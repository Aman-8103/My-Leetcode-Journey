class Solution {
    public String digitSum(String s, int k) {
        // Repeat the process while the string length exceeds k
        while (s.length() > k) {
            StringBuilder nextRound = new StringBuilder();
            
            // Step through the string in chunks of size k
            for (int i = 0; i < s.length(); i += k) {
                int groupSum = 0;
                
                // Sum the digits within the current chunk
                for (int j = i; j < Math.min(s.length(), i + k); j++) {
                    groupSum += s.charAt(j) - '0';
                }
                
                // Append the full sum to the new string
                nextRound.append(groupSum);
            }
            
            // Update s for the next iteration
            s = nextRound.toString();
        }
        return s;
    }
}
