class Solution {
    public int sortPermutation(int[] arr) {
        int n=arr.length;
        int max=-1;
        for(int i=0;i<n;i++){
            if(arr[i] != i){
                if(max==-1){
                    max=arr[i];
                }else{
                    max&=arr[i];
                }
            }
        }
        return max==-1?0:max;
    }
}