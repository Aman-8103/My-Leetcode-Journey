class Solution {
    public int countCommas(int n) {
        int count=0;
        if(n<1000) return 0;

        for(int i=1000;i<=n;i++){
            if(i>=1000 && i<1000000)count++;
        }
        return count;
    }
}