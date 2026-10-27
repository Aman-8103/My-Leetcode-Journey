class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid=new char[m][n];

        for(char[] i:grid) Arrays.fill(i,'#'); //fill grids with'#''

        Arrays.fill(grid[0],'.'); //open the first row

        for(int i=0;i<m;i++) grid[i][n-1]='.'; //open the last column

        String[] ans=new String[m];
        for(int i=0;i<m;i++){
            ans[i]=new String(grid[i]);
        }
        return ans;

        
    }
}