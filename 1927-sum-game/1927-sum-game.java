class Solution {
    public boolean sumGame(String num) {
        int n=num.length();
        char[] arr=num.toCharArray();
        int half=n/2;

        int leftsum=0,rightsum=0;
        int leftq=0,rightq=0;

        for(int i=0;i<half;i++){
            if(arr[i] == '?'){
                leftq++;
            }else{
                leftsum+=arr[i]-'0';
            }
        }

        for(int i=half;i<n;i++){
            if(arr[i] == '?'){
                rightq++;
            }else{
                rightsum+=arr[i]-'0';
            }
        }

        return (leftsum-rightsum)*2 != (rightq-leftq)*9;
        
    }
}