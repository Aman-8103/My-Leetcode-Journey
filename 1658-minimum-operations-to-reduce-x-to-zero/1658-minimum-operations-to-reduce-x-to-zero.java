class Solution {
    public int minOperations(int[] arr, int x) {
        int n=arr.length;
        int total=0;
        
        for(int i:arr) total+=i;
        int target=total-x;

        if(target<0) return-1;
        if(target == 0) return n;
        int left=0;
        int windowsum=0;
        int maxlength=-1;

        for(int i=0;i<n;i++){
            windowsum+=arr[i];
            while(windowsum>target && left<=i){
                windowsum-=arr[left++];
            }
            if(windowsum == target) maxlength=Math.max(maxlength,i-left+1);
        }

        if(maxlength == -1) return -1;
        return n-maxlength;
    }
}