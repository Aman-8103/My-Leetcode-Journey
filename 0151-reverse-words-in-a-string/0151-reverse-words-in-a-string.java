class Solution {
    public String reverseWords(String s) {
        StringBuilder result=new StringBuilder();
        int n=s.length();
        int j=n-1;

        while(j>=0){
            while(j>=0 && s.charAt(j) == ' ') j--;
            int end=j;
            if(j<0) break;
            while(j>=0 && s.charAt(j) != ' ') j--;
            String word=s.substring(j+1,end+1);
            if(result.length()>0) result.append(' ');
            result.append(word);
        }
        return result.toString();
        
    }
}