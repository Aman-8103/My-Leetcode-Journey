class Solution {
    public boolean checkDivisibility(int n) {
        int num=n;
        int sum=0;
        int prod=1;

        while(n>0){
            int digit=n%10;
            sum=sum + digit;
            prod*=digit;
            n/=10;
        }
        if(num % (sum+prod) == 0) return true;
        return false;
    }
}