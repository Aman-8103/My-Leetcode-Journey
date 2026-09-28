class Solution {
    public int maxDepth(String s) {
        int n=s.length();
        int max=0;
        int count=0;
        for(char ch:s.toCharArray()){
            if(ch=='(') count++;
            max=Math.max(max,count);
            if(ch==')') count--;
        }
        return max;
    }
}