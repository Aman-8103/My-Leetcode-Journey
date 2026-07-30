class Solution {
    public int minimumPushes(String word) {
        int n=word.length();

        int[] freq=new int[26];
        for(char ch:word.toCharArray()){
            freq[ch - 'a']++;
        }
        
        Arrays.sort(freq);
        int push=0;
        for(int i=25;i>=0;i--){
            if(freq[i] == 0) break;
            int k=25-i;

            int multiplier=(k/8)+1;
            push+=freq[i] * multiplier;
        }
        return push;
    }
}