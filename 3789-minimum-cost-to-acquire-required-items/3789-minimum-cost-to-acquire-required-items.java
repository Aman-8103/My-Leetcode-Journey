class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long a=(long) cost1*need1 + (long)cost2*need2;//taking spearately all
        long b=(long) costBoth*Math.max(need1,need2);//taking combo all

        int min=Math.min(need1,need2);
        long c= (long) costBoth*min+ (long)cost1*(need1-min)+ (long)cost2*(need2-min); //for ovarlapping and remaining needs.
        return Math.min(a,Math.min(b,c));
    }
}