class Solution { 
    public String shortestBeautifulSubstring(String s, int k) { 
        int n=s.length(); 
        int left=0; 
        int ones=0; 
        String ans=""; 
        for(int i=0;i<n;i++){ 
            if(s.charAt(i)=='1') ones++; 
            while(ones > k){ 
                if(s.charAt(left) == '1'){ 
                    ones--; 
                } 
                left++; 
            } 
            if(ones == k){ 
                while(s.charAt(left) == '0'){ 
                    left++; 
                } 
                
                // ADJUSTMENT: Calculate length using indices first to avoid creating strings
                int currentLen = i - left + 1; 
                
                if(ans.equals("") || currentLen < ans.length()){ 
                    ans = s.substring(left, i + 1); 
                } else if (currentLen == ans.length()) {
                    String current = s.substring(left, i + 1); 
                    if (current.compareTo(ans) < 0) {
                        ans = current; 
                    }
                }
            } 
        } 
        return ans; 
    } 
}
