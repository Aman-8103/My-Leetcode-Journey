class Solution {
    public int splitArray(int[] arr, int k) {
        int n=arr.length;
        if(k > n) return -1;

        int max=Integer.MIN_VALUE;
        int sum=0;
        for(int i:arr){
            max=Math.max(i,max);
            sum+=i;
        }
        int low=max;
        int high=sum;
        int ans=-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(possible(arr,mid,k)){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return ans;
    }

    boolean possible(int[] arr, int mid, int k){
        int n=arr.length;
        int count=1;
        int sum=0;
        for(int i=0;i<n;i++){
            if(sum+arr[i] > mid){
                count++;
                sum=arr[i];
            }else{
                sum+=arr[i];
            }
        }
        return count<=k;
    }

}