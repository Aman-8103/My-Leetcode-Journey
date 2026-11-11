class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // long a=(long) cost1*need1 + (long)cost2*need2;//taking spearately all
        // long b=(long) costBoth*Math.max(need1,need2);//taking combo all

        // int min=Math.min(need1,need2);
        // long c= (long) costBoth*min+ (long)cost1*(need1-min)+ (long)cost2*(need2-min); //for ovarlapping and remaining needs.
        // return Math.min(a,Math.min(b,c));

        long totalcost=0;
        if(cost1+cost2 <=costBoth) totalcost=(long)cost1*need1+(long)cost2*need2;
        else{
            int min=Math.min(need1,need2);
            totalcost=(long)costBoth*min;
            need1-=min;
            need2-=min;
            if(need1>0) totalcost+= (long) need1*Math.min(cost1,costBoth);
            else if(need2>0) totalcost+=(long)need2*Math.min(cost2,costBoth);
        }
        return totalcost;
    }
}