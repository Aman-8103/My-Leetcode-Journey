class Solution {
    public int totalFruit(int[] fruits) {
        int n=fruits.length;
        int unique=0;
        int i=0,j=0;
        int[] hash=new int[n+1];

        for(i=0;i<n;i++){
            if(hash[fruits[i]] == 0) unique++;
            hash[fruits[i]]++;

            if(unique > 2){
                hash[fruits[j]]--;
                if(hash[fruits[j]] == 0) unique--;
                j++;
            }
        }

        return i-j;
    }
}