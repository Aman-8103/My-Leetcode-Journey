class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        int[] result=new int[n+m];
        int i=0,j=0;
        int index=0;
        while(i<n && j<m){
            if(nums1[i]<=nums2[j]){
                result[index++]=nums1[i++];
            }else{
                result[index++]=nums2[j++];
            }

        }
        while(i<n) result[index++]=nums1[i++];
        while(j<m) result[index++]=nums2[j++];

        int size=n+m;
        if(size%2 == 0){
            double sum=result[size/2] + result[(size/2)-1];
            return sum/2.0;
        }else{
            return result[size/2];
        }
        
    }
}