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
        int first=Math.min(minidx,maxidx);
        int second=Math.max(minidx,maxidx);

        int leftremove=second+1;
        int rightremove=n-first;
        int both=(first+1) +(n-second);

        return Math.min(Math.min(leftremove,rightremove),both);
        
    }
}