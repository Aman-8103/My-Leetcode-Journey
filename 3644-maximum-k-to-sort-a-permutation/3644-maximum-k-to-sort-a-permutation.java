class Solution {
    public int sortPermutation(int[] arr) {
        int n=arr.length;
        int max=-1;
        boolean sorted=true;

        for(int i=0;i<n;i++){
            if(arr[i] != i){
                max&=arr[i];
                sorted=false;
            }
        }
        return sorted?0:max;
    }
}