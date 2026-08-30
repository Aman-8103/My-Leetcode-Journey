class Solution {
    public int minimumDeletions(int[] arr) {
        int n=arr.length;
        if(n<=2) return n;
        int minidx=0,maxidx=0;

        for(int i=0;i<n;i++){
            if(arr[i]<arr[minidx]) minidx=i;
            if(arr[i]>arr[maxidx]) maxidx=i;
        }
        int leftremove=Math.max(minidx,maxidx)+1;
        int rightremove=n-Math.min(minidx,maxidx);
        int both=(Math.min(minidx,maxidx)+1) +(n-Math.max(minidx,maxidx));
        return Math.min(Math.min(leftremove,rightremove),both);
        
    }
}