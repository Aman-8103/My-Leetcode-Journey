class Solution {
    public String reverseWords(String s) {
        StringBuilder result=new StringBuilder();
        int n=s.length();
        char[] arr=s.toCharArray();
        reverse(arr,0,n-1);

        int i=0;
        while(i<n){
            while(i<n && arr[i] == ' ') i++;
            int start=i;
            if(i>=n) break;

            while(i<n && arr[i] != ' ') i++;
            int end=i-1;
            reverse(arr,start,end);
            if(result.length() >0) result.append(' ');
            result.append(arr,start,end-start+1);

        }

        return result.toString();
    }


    void reverse(char[] arr, int start, int end){
        while(start < end){
            char temp=arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
            start++;
            end--;
        }

    }
}