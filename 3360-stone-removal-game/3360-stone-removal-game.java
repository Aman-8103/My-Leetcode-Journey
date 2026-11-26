class Solution {
    public boolean canAliceWin(int n) {
        if(n<10) return false;
        int turn=0;
        int removal=10;
        while(n>=removal){
            n-=removal;
            removal--;
            turn++;
        }
        return turn%2==1;        
    }
}