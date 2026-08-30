class Solution {
    public int minimumDeletions(int[] arr) {
        int n=arr.length;
        if(n<=2) return n;
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        int minidx=-1,maxidx=-1;

        for(int i=0;i<n;i++){
            if(arr[i]<min){
                min=arr[i];
                minidx=i;
            }
            if(arr[i]>max){
                max=arr[i];
                maxidx=i;
            }
        }
        int leftremove=Math.max(minidx,maxidx)+1;
        int rightremove=n-Math.min(minidx,maxidx);
        int both=(Math.min(minidx,maxidx)+1) +(n-Math.max(minidx,maxidx));
        return Math.min(Math.min(leftremove,rightremove),both);
        
    }
}