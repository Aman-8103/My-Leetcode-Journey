class Solution {
    public int firstStableIndex(int[] arr, int k) {
        int n=arr.length;
        int max=arr[0];

        for(int i=0;i<n;i++){
            max=Math.max(arr[i],max);
            int min=arr[i];
            for(int j=i;j<n;j++){
                min=Math.min(arr[j],min);
            }
            if(max-min <= k) return i;
        }
        return -1;
        
    }
}