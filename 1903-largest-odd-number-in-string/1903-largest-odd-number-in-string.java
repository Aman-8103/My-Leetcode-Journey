class Solution {
    public String largestOddNumber(String num) {
        int n=num.length();

        int start=0;
        int end=-1;
        for(int i=n-1;i>=0;i--){
            char digit=num.charAt(i);
            if(digit %2 != 0){
                end=i;
                break;
            }
        }
        if(end == -1) return "";
        return num.substring(start,end+1);
    }
}