class Solution {
    public int minMoves2(int[] arr) {
        Arrays.sort(arr);
        int med=arr[arr.length/2];
        int moves=0;
        for(int i:arr) moves+=Math.abs(i-med);
        return moves;
    }
}