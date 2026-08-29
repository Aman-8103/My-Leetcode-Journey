class Solution {
    public int[] searchRange(int[] arr, int target) {
        int n=arr.length;

        int low=lower(arr,target);
        if(low == -1) return new int[]{-1,-1};
        int high=upper(arr,target);
    
        return new int[]{low,high};
    }

    int lower(int[] arr,int target){
        int low=0;
        int high=arr.length-1;
        int first=-1;

        while(low<=high){
            int mid=(low+high)/2;
            if(arr[mid] == target){
                first=mid;
                high=mid-1;
            }else if(arr[mid]<target){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return first;
    }
    int upper(int[] arr, int target){
        int low=0;
        int high=arr.length-1;
        int last=-1;

        while(low<=high){
            int mid=(low+high)/2;
            if(arr[mid] == target){
                last=mid;
                low=mid+1;
            }else if(arr[mid] < target){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return last;
    }
}