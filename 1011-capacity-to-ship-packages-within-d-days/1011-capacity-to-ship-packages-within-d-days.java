class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int n=weights.length;
        int min=0;
        int max=0;
        for(int i:weights){
            if(i > min) min=i;
            max+=i;
        }

        int low=min,high=max;
        int ans=-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(possible(weights,mid,days)){
                ans=mid;
                high=mid-1;
            }
            else{
                low=mid+1;
            }
        }
        return ans;
    }

    boolean possible(int[] arr,int mid,int days){
        int daysreq=1;
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(sum > mid){
                daysreq++;
                sum=arr[i];
                if(daysreq>days)return false;
            }
        }

        return daysreq<=days;
    }
}