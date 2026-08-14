class Solution {
    public int maximumLengthSubstring(String s) {
        int n=s.length();
        int max=0;

        for(int i=0;i<n;i++){
            int[] freq=new int[26];
            for(int j=i;j<n;j++){
                char ch=s.charAt(j);
                freq[ch - 'a']++;

                if(freq[ch - 'a'] > 2){
                    break;
                }

                max=Math.max(max,j+1-i);
            }
        }

        return max;
    }
}