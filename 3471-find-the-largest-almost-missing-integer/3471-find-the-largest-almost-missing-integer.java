class Solution {
    public int largestInteger(int[] arr, int k) {
        int n=arr.length;

        if(k==1){
            int[] count=new int[51];
            for(int i:arr){
                count[i]++;
            }
            for(int i=50;i>=0;i--){
                if(count[i] == 1) return i;
            }
            return -1;
        }

        if(k==n){
            int max=-1;
            for(int i:arr){
                if(i>max) max=i;
            }
            return max;
        }
        
        int start=arr[0];
        int end=arr[n-1];

        if(start==end) return -1;
        
        for(int i=1;i<n-1;i++){
            if(arr[i] == start) start=-1;
            if(arr[i] == end) end=-1;
        }
        return Math.max(start,end);
    }
}