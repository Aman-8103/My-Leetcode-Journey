class Solution {
    public int minimizeSum(int[] arr) {
        int n=arr.length;
        Arrays.sort(arr);
        int case1=arr[n-1]-arr[2];
        int case2=arr[n-3]-arr[0];
        int case3=arr[n-2]-arr[1];
        return Math.min(case1,Math.min(case2,case3));
    }
}