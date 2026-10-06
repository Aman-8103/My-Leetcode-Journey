class Solution {
    public int minAddToMakeValid(String s) {
        int n=s.length();
        int leftneed=0;
        int rightneed=0;

        for(int i=0;i<n;i++){
            char ch=s.charAt(i);
            if(ch=='(') rightneed++;
            else{
                if(rightneed>0){
                    rightneed--;
                }else{
                    leftneed++;
                }
            }
        }
        return rightneed+leftneed;
        
    }
}