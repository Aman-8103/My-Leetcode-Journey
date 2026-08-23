class Solution {
    public boolean sumGame(String num) {
        int n=num.length();
        int leftsum=0,rightsum=0;
        int leftq=0,rightq=0;

        for(int i=0;i<n/2;i++){
            char ch=num.charAt(i);
            if(ch == '?'){
                leftq++;
            }else{
                leftsum+=ch-'0';
            }
        }

        for(int i=n/2;i<n;i++){
            char ch=num.charAt(i);
            if(ch == '?'){
                rightq++;
            }else{
                rightsum+=ch-'0';
            }
        }

        return (leftsum-rightsum)*2 != (rightq-leftq)*9;
        
    }
}