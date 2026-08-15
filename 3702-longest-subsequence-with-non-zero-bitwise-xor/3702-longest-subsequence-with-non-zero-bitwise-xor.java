class Solution {
    public int longestSubsequence(int[] arr) {
        int n=arr.length;
        int xor=0;
        int zero=0;

        for(int i:arr){
            xor^=i;
            if(i == 0){
                zero++;
            }
        }

        if(zero == n) return 0;
        if(xor != 0) return n;
        
        return n-1;
    }
}