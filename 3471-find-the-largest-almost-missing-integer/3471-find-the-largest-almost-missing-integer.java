class Solution {
    public int largestInteger(int[] arr, int k) {
        int n=arr.length;

        if(k==1){
            int[] count=new int[51];
            for(int i:arr){
                count[i]++;
            }
            for(int i=50;i>=0;i--){
                if(count[i] == 1) return i;
            }
            return -1;
        }

        if(k==n){
            int max=-1;
            for(int i:arr){
                if(i>max) max=i;
            }
            return max;
        }
        
        int first=arr[0];
        int last=arr[n-1];

        if(first==last) return -1;
        boolean firstvalid=true;
        boolean lastvalid=true;

        for (int i=1; i<n; i++) {
            if (arr[i]==first) {
                firstvalid=false;
            }
        }
        for (int i=0; i<n-1; i++) {
            if (arr[i]==last) {
                lastvalid=false;
            }
        }

        if(firstvalid && lastvalid){
            return Math.max(first,last);
        }else if(firstvalid){
            return first;
        }else if(lastvalid){
            return last;
        }

        return -1;
    }
}