class Solution {
    public int smallestIndex(int[] arr) {
        int n=arr.length;

        for(int i=0;i<n;i++){
            int sum=0;
            while(arr[i]>0){
                sum+=arr[i]%10;
                arr[i]/=10;
            }
            if(sum == i) return i;
        }
        return -1;
    }
}