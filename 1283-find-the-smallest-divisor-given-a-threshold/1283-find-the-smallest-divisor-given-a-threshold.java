class Solution {
    public int smallestDivisor(int[] arr, int threshold) {
        int n=arr.length;
        int min=1;
        int max=Integer.MIN_VALUE;
        for(int i:arr){
            max=Math.max(i,max);
        }

        int low=min,high=max;
        int ans=-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(possible(arr,mid,threshold)){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return ans;
    }

    boolean possible(int[] arr,int mid,int threshold){
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=(arr[i]+mid-1)/mid;
            if(sum > threshold) return false;
        }
        return true;
    }
}