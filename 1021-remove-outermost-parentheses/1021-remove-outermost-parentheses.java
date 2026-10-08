class Solution {
    public String removeOuterParentheses(String s) {
        int n=s.length();
        int count=0;
        StringBuilder ans=new StringBuilder();

        for(char c:s.toCharArray()){
            if(c == ')') count--;
            if(count != 0) ans.append(c);
            if(c == '(') count++;
        }
        return ans.toString();
    }
}