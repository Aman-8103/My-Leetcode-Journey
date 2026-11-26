class Solution(object):
    def canAliceWin(self, n):
        if(n<10):
            return False
        removal=10
        turn=0
        while(n>=removal):
            n-=removal
            removal-=1
            turn+=1  

        return turn%2==1      