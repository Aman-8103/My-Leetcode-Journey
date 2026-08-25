class Solution {
    public int findMaxConsecutiveOnes(int[] arr) {
        int n=arr.length;
        int max=0;
        int count=0;

        for(int i=0;i<n;i++){
            if(arr[i]==1){
                count++;
                if(max < count) max=count;
            }else{
                count=0;
            }
        }
        return max;
    }
}