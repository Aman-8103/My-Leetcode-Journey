class Solution {
    public boolean uniformArray(int[] arr) {
        int n=arr.length;
        int min=Integer.MAX_VALUE;
        for(int i:arr){
            min=Math.min(i,min);
        }
        if(min%2 != 0) return true; //if min number is odd then definetely possible

        for(int i:arr){ 
        // min is even then check whole array if any odd comes then not possible to convert all to even 
        // because: (odd-even=odd) but we need even-evnn=even only.
            if(i%2 !=0) return false;
        }
        return true;
    }
}