class Solution {
    public double myPow(double x, int n) {
        double ans=1;
        long newn=n;
        if(newn<0){
            x=1/x;
            newn=-newn;
        }

        while(newn>0){
            if(newn%2 !=0){
                ans*=x;
            }
            x*=x;
            newn/=2;
        }
        
        return ans; 
    }
}