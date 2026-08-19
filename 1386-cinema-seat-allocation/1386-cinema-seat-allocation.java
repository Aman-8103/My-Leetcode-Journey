class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int ans = 0;
        java.util.Map<Integer, Integer> rowToSeats = new java.util.HashMap<>();
        
        for (int[] r : reservedSeats) {
            int row = r[0];
            int seat = r[1];
            rowToSeats.put(row, rowToSeats.getOrDefault(row, 0) | (1 << (seat - 1)));
        }
        
        for (int seats : rowToSeats.values()) {
            boolean leftFree = (seats & 0b0111100000) == 0;
            boolean rightFree = (seats & 0b0000011110) == 0;
            boolean middleFree = (seats & 0b0001111000) == 0;
            
            if (leftFree && rightFree) {
                ans += 2;
            } else if (leftFree || rightFree || middleFree) {
                ans += 1;
            }
        }
        
        return ans + (n - rowToSeats.size()) * 2;
    }
}
