class Solution {
    public int missingMultiple(int[] arr, int k) {
       Arrays.sort(arr);
       int multiple=k;
       for(int i:arr){
        if(i < multiple) continue;
        if(i == multiple) multiple+=k;
        else return multiple;
       }
       return multiple;
    }
}