class Solution {
    public String removeOuterParentheses(String s) {
        int n=s.length();
        int count=0;
        StringBuilder ans=new StringBuilder();

        for(char i:s.toCharArray()){
            if(i == ')') count--;
            if(count !=0) ans.append(i);
            if(i == '(') count++;
        }
        return ans.toString();
        
    }
}