class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        char[] arr = num.toCharArray();
        int half = n / 2;

        int leftsum = 0, rightsum = 0;
        int leftq = 0, rightq = 0;

        for (int i = 0; i < half; i++) {
            if (arr[i] == '?') {
                leftq++;
            } else {
                leftsum += arr[i] - '0';
            }
        }

        for (int i = half; i < n; i++) {
            if (arr[i] == '?') {
                rightq++;
            } else {
                rightsum += arr[i] - '0';
            }
        }

        // --- NATURAL BUG INTRODUCED HERE FOR YOUR TEST CASE ---
        // Log aksar simple logic lagate hain ki difference ko 4.5 se float check karein,
        // ya fir strict check lagate waqt sign (positive/negative) ko ignore kar dete hain.
        int sumDiff = leftsum - rightsum;
        int qDiff = rightq - leftq;

        // Agar hum normal division approach sochein (jo aam log jaldi me likhte hain):
        // Yeh line aapke "All ? and one 1" waale case par galat (False) return kar degi!
        return sumDiff != (qDiff * 4.5); 
    }
}
