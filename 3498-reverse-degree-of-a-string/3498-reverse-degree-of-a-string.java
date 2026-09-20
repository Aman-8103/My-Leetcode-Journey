class Solution {
    public int reverseDegree(String s) {
        int n=s.length();
        int sum=0;

        for(int i=0;i<n;i++){
            int index=s.charAt(i)-'a';
            int reverse=26-index;
            sum+=reverse*(i+1);
        }
        return sum;
    }

   
}