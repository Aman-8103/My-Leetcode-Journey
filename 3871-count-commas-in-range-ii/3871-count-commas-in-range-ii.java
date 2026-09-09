class Solution {
    public long countCommas(long n) {
        if(n<1000) return 0;

        if(n>=1000 && n<1000000) return n-999;
        if(n>=1000000 && n<1000000000) return 999000L+(n-999999L)*2;
        if(n>=1000000000L && n<1000000000000L)return 1998999000L+(n-999999999L)*3;
        if (n >= 1000000000000L && n < 1000000000000000L) return 2998998999000L+(n-999999999999L)*4;
        return 3998998998999000L + (n - 999999999999999L) * 5;
    }
}