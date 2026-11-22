class Solution(object):
    def maxDistance(self, colors):
        n=len(colors)
        max=0
        for i in range(0,n):
            for j in range(i,n):
                dist=0
                if(colors[i]!=colors[j]):
                    dist=abs(j-i)
                
                if(dist>max):
                    max=dist
        
        return max
        
        