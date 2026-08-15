class Solution {
    public int longestSubsequence(int[] arr) {
        int n=arr.length;
        int xor=0;
        boolean zero=true;

        for(int i:arr){
            xor^=i;

            if(i != 0){
                zero=false;
            }
        }

        if(zero) return 0;
        
        return (xor==0)? n-1:n;
    }
}