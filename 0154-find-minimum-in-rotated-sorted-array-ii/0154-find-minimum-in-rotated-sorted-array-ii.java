class Solution {
    public int findMin(int[] arr) {
        int n=arr.length;
        int low=0,high=n-1;
        int ans=5001;

        while(low<=high){
            int mid=(low+high)/2;
            if(arr[low]==arr[mid] && arr[mid]==arr[high]){
                ans=Math.min(ans,arr[low]);
                low++;
                high--;
                continue;
            }
            if(arr[low]<=arr[mid]){
                ans=Math.min(ans,arr[low]);
                low=mid+1;
            }else{
                ans=Math.min(ans,arr[mid]);
                high=mid-1;
            }
        }
        return ans;
    }
}