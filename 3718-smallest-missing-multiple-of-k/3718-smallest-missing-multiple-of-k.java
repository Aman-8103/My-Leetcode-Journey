class Solution {
    public int missingMultiple(int[] arr, int k) {
        HashSet<Integer> hash=new HashSet<>();
        for(int i:arr){
            hash.add(i);
        }
        int multiplier=1;

        while(true){
            int multi=k*multiplier;
            if(!hash.contains(multi)) return multi;
            multiplier++;
        }
    }
}